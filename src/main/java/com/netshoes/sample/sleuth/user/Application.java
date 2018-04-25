package com.netshoes.sample.sleuth.user;

import com.github.javafaker.Faker;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJpaRepositories(considerNestedRepositories = true)
@Slf4j
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @RestController
  @RequestMapping("/users")
  @AllArgsConstructor
  public class Controller {
    private final Faker faker = new Faker();
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
      return persistedUser;
    }
  }

  @Data
  @Entity
  @Table(name = "users")
  public static class User {
    @Id private String email;
    private String name;
  }

  @Repository
  public interface UserRepository extends CrudRepository<User, String> {}

  @ResponseStatus(HttpStatus.CONFLICT)
  public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
      super("User already exists");
    }
  }
}
