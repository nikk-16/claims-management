package com.claims.manage.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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
