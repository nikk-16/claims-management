package com.claims.manage.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
public class Users {
  @Id
  private String id;
  private String username;
  private String email;
  private Long mobile;
  private String password;
}
