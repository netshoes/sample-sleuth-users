package com.netshoes.sample.sleuth.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
  @Id private String email;
  private String name;
  private String password;
}
