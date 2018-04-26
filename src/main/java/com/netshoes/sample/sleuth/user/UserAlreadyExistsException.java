package com.netshoes.sample.sleuth.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends Exception {
  private static final long serialVersionUID = 8895764935217040418L;

  public UserAlreadyExistsException() {
    super("User already exists");
  }
}
