package com.claims.manage.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "insurance_policies")
public class InsurancePolicies {
    @Id
    private String id;
    private String type;
    private int amount;
    private int maxClaim;
    private List<String> description;
}
