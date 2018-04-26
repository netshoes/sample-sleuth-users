package com.netshoes.sample.sleuth.user;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @RestController
  @RequestMapping("/users")
  @AllArgsConstructor
  public class Controller {
    private final Faker faker = new Faker();
    private RestTemplate restTemplate;
    private UserRepository userRepository;

    @GetMapping
    public Iterable<User> findAll() {
      return userRepository.findAll();
    }

    @PostMapping("/someone")
    @ResponseStatus(HttpStatus.CREATED)
    public User createSomeone() throws UserAlreadyExistsException {
      final User user = new User();
      user.setEmail(faker.internet().emailAddress());
      user.setName(faker.name().fullName());
      user.setPassword(RandomStringUtils.randomAlphabetic(6));
      return create(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) throws UserAlreadyExistsException {
      User persistedUser = userRepository.findOne(user.getEmail());
      if (persistedUser != null) {
        throw new UserAlreadyExistsException();
      }
      persistedUser = userRepository.save(user);
      log.info("User {} created.", user.getEmail());

      final Notification notification = new Notification(user.getEmail(), buildContent(user));
      restTemplate.postForObject("http://localhost:8081/notify", notification, String.class);
      log.info("Notification about initial password sent to {}", user.getEmail());

      return persistedUser;
    }

    private String buildContent(User user) {
      return String.format(
          "Hi %s, your initial password is %s", user.getName(), user.getPassword());
    }
  }
}
