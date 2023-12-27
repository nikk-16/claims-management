package com.claims.manage.components;

import org.springframework.stereotype.Component;

@Component
public class ApplyClaims {
    private String policyNo;
    private String name;

    public ApplyClaims() {
    }

    public ApplyClaims(String policyNo, String name, String insuranceType, String claimReason, Double estimatedAmount) {
        this.policyNo = policyNo;
        this.name = name;
        this.insuranceType = insuranceType;
        this.claimReason = claimReason;
        this.estimatedAmount = estimatedAmount;
    }

    private String insuranceType;
    private String claimReason;
    private Double estimatedAmount;

    @Override
    public String toString() {
        return "ApplyClaims{" +
                "policyNo='" + policyNo + '\'' +
                ", name='" + name + '\'' +
                ", insuranceType='" + insuranceType + '\'' +
                ", claimReason='" + claimReason + '\'' +
                ", estimatedAmount='" + estimatedAmount + '\'' +
                '}';
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getClaimReason() {
        return claimReason;
    }

    public void setClaimReason(String claimReason) {
        this.claimReason = claimReason;
    }

    public Double getEstimatedAmount() {
        return estimatedAmount;
    }

    public void setEstimatedAmount(Double estimatedAmount) {
        this.estimatedAmount = estimatedAmount;
    }
}
