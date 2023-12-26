package com.claims.manage.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "claims")
@Data
public class Claims {
  @Id
  private String id;
  private String userId;
  private Double amount;
  private String insuranceId;

  private String type;
  private 
  
}
