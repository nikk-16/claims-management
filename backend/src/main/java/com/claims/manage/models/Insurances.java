package com.claims.manage.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "insurance")
public class Insurances {
    @Id
    private String id;
    private String username;
    private String type;
    private Double amount;
    private Date startDate;
    private Date endDate;
    private Double maxClaim;
}
