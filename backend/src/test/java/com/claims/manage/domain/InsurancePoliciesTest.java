package com.claims.manage.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsurancePoliciesTest {

    @Test
    public void testInsurancePolicies() {
        InsurancePolicies insurancePolicies = new InsurancePolicies();

        insurancePolicies.setId("1");
        assertEquals("1", insurancePolicies.getId());

        insurancePolicies.setType("Test Type");
        assertEquals("Test Type", insurancePolicies.getType());

        insurancePolicies.setAmount(200);
        assertEquals(200, insurancePolicies.getAmount());

        insurancePolicies.setMaxClaim(1000);
        assertEquals(1000, insurancePolicies.getMaxClaim());

        List<String> description = Arrays.asList("Test Description 1", "Test Description 2");
        insurancePolicies.setDescription(description);
        assertEquals(description, insurancePolicies.getDescription());
    }
}
