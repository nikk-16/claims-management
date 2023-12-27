package com.claims.manage.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "claims")

public class Claims {
  public Claims() {
  }

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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Claims{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", amount=" + amount +
            ", insuranceId='" + insuranceId + '\'' +
            ", type='" + type + '\'' +
            ", reason='" + reason + '\'' +
            ", estimate='" + estimate + '\'' +
            ", releasedAmount='" + releasedAmount + '\'' +
            ", status='" + status + '\'' +
            '}';
  }
  public Claims(String id, String name, Double amount, String insuranceId, String type, String reason, Double estimate, String releasedAmount, String status) {
    this.id = id;
    this.name = name;
    this.amount = amount;
    this.insuranceId = insuranceId;
    this.type = type;
    this.reason = reason;
    this.estimate = estimate;
    this.releasedAmount = releasedAmount;
    this.status = status;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getInsuranceId() {
    return insuranceId;
  }

  public void setInsuranceId(String insuranceId) {
    this.insuranceId = insuranceId;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public Double getEstimate() {
    return estimate;
  }

  public void setEstimate(Double estimate) {
    this.estimate = estimate;
  }

  public String getReleasedAmount() {
    return releasedAmount;
  }

  public void setReleasedAmount(String releasedAmount) {
    this.releasedAmount = releasedAmount;
  }
}
