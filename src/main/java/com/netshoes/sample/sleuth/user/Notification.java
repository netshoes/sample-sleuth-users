package com.netshoes.sample.sleuth.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Notification {
  private String to;
  private String content;
}
