package com.claims.manage.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplyClaimsTest {

    @Test
    public void testApplyClaims() {
        ApplyClaims applyClaims = new ApplyClaims();

        applyClaims.setPolicyNo("12345");
        assertEquals("12345", applyClaims.getPolicyNo());

        applyClaims.setName("Test Name");
        assertEquals("Test Name", applyClaims.getName());

        applyClaims.setInsuranceType("Test Type");
        assertEquals("Test Type", applyClaims.getInsuranceType());

        applyClaims.setClaimReason("Test Reason");
        assertEquals("Test Reason", applyClaims.getClaimReason());

        applyClaims.setEstimatedAmount(1000.0);
        assertEquals(1000.0, applyClaims.getEstimatedAmount());

        String expectedToString = "ApplyClaims{policyNo='12345', name='Test Name', insuranceType='Test Type', claimReason='Test Reason', estimatedAmount='1000.0'}";
        assertEquals(expectedToString, applyClaims.toString());

        ApplyClaims applyClaims2 = new ApplyClaims("12345", "Test Name", "Test Type", "Test Reason", 1000.0);
        assertEquals("12345", applyClaims2.getPolicyNo());
        assertEquals("Test Name", applyClaims2.getName());
        assertEquals("Test Type", applyClaims2.getInsuranceType());
        assertEquals("Test Reason", applyClaims2.getClaimReason());
        assertEquals(1000.0, applyClaims2.getEstimatedAmount());
        assertEquals(expectedToString, applyClaims2.toString());
    }
}