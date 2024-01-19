package com.claims.manage.domain;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClaimsTest {

    @Test
    public void testClaimsConstructor() {
        Claims claim = new Claims();
        assertNotNull(claim);
    }

    @Test
    public void testClaimsParamsConstructor() {
        String name = "Test Name";
        String insuranceType = "Test Insurance Type";
        String claimReason = "Test Claim Reason";
        Double estimatedAmount = 1000.0;

        Claims claim = new Claims(name, insuranceType, claimReason, estimatedAmount);

        assertEquals(name, claim.getName());
        assertEquals(insuranceType, claim.getType());
        assertEquals(claimReason, claim.getReason());
        assertEquals(estimatedAmount, claim.getEstimate());
    }

    @Test
    public void testClaims() {
        Claims claims = new Claims("Test Name", "Test Type", "Test Reason", 100.0);

        assertEquals("Test Name", claims.getName());
        claims.setType("Test Type");
        assertEquals("Test Type", claims.getType());
        claims.setReason("Test Reason");
        assertEquals("Test Reason", claims.getReason());
        claims.setEstimate(100.0);
        assertEquals(100.0, claims.getEstimate());

        claims.setId("1");
        assertEquals("1", claims.getId());

        claims.setAmount(200.0);
        assertEquals(200.0, claims.getAmount());

        claims.setInsuranceId("Test InsuranceId");
        assertEquals("Test InsuranceId", claims.getInsuranceId());

        claims.setReleasedAmount("300.0");
        assertEquals("300.0", claims.getReleasedAmount());

        claims.setStatus("Test Status");
        assertEquals("Test Status", claims.getStatus());
    }
}