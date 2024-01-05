package com.claims.manage.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "insurance")
@Data
public class Insurance {
    @Id
    private String id;
    private String username;
    private String type;
    private Double amount;
    private String startDate;
    private String endDate;
    private Double maxClaim;
}
