package com.claims.manage.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
public class Users {
  @Id
  private String id;
  private String name;
  private String email;
  private Long mobile;
  private String password;
}
