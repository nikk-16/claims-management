package com.claims.manage.model;

import java.util.Date;

public class NewInsurance {
    private String username;
    private String type;
    private Double amount;
    private Date startDate;
    private Date endDate;
    private Double maxClaim;

    public NewInsurance() {
    }

    public NewInsurance(String username, String type, Double amount, Date startDate, Date endDate, Double maxClaim) {
        this.username = username;
        this.type = type;
        this.amount = amount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxClaim = maxClaim;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getMaxClaim() {
        return maxClaim;
    }

    public void setMaxClaim(Double maxClaim) {
        this.maxClaim = maxClaim;
    }

    @Override
    public String toString() {
        return "NewInsurance{" +
                "username='" + username + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", maxClaim=" + maxClaim +
                '}';
    }
}
