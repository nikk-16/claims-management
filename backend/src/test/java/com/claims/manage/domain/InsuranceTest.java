package com.claims.manage.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsuranceTest {

    @Test
    public void testInsurance() {
        Insurance insurance = new Insurance();

        insurance.setId("1");
        assertEquals("1", insurance.getId());

        insurance.setUsername("Test User");
        assertEquals("Test User", insurance.getUsername());

        insurance.setType("Test Type");
        assertEquals("Test Type", insurance.getType());

        insurance.setAmount(200.0);
        assertEquals(200.0, insurance.getAmount());

        insurance.setStartDate("2022-01-01");
        assertEquals("2022-01-01", insurance.getStartDate());

        insurance.setEndDate("2022-12-31");
        assertEquals("2022-12-31", insurance.getEndDate());

        insurance.setMaxClaim(1000.0);
        assertEquals(1000.0, insurance.getMaxClaim());
    }
}
