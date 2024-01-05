package com.claims.manage.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "claims")
@Data
public class Claims {
  @Id
  private String id;
  private String name;
  private Double amount;
  private String insuranceId;
  private String type;
  private String reason;
  private Double estimate;
  private String releasedAmount;
  private String status;

  public Claims(String name, String insuranceType, String claimReason, Double estimatedAmount) {
    this.name = name;
    this.type = insuranceType;
    this.reason = claimReason;
    this.estimate = estimatedAmount;
  }
}

