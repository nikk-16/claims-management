package com.claims.manage.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewInsuranceTest {

    @Test
    public void testNewInsurance() {
        NewInsurance newInsurance = new NewInsurance();

        newInsurance.setUsername("TestUser");
        assertEquals("TestUser", newInsurance.getUsername());

        newInsurance.setType("TestType");
        assertEquals("TestType", newInsurance.getType());

        newInsurance.setAmount(1000.0);
        assertEquals(1000.0, newInsurance.getAmount());

        Date startDate = new Date();
        newInsurance.setStartDate(startDate);
        assertEquals(startDate, newInsurance.getStartDate());

        Date endDate = new Date();
        newInsurance.setEndDate(endDate);
        assertEquals(endDate, newInsurance.getEndDate());

        newInsurance.setMaxClaim(500.0);
        assertEquals(500.0, newInsurance.getMaxClaim());

        String expectedToString = "NewInsurance{username='TestUser', type='TestType', amount=1000.0, startDate=" + startDate + ", endDate=" + endDate + ", maxClaim=500.0}";
        assertEquals(expectedToString, newInsurance.toString());

        NewInsurance newInsurance2 = new NewInsurance("TestUser", "TestType", 1000.0, startDate, endDate, 500.0);
        assertEquals("TestUser", newInsurance2.getUsername());
        assertEquals("TestType", newInsurance2.getType());
        assertEquals(1000.0, newInsurance2.getAmount());
        assertEquals(startDate, newInsurance2.getStartDate());
        assertEquals(endDate, newInsurance2.getEndDate());
        assertEquals(500.0, newInsurance2.getMaxClaim());
        assertEquals(expectedToString, newInsurance2.toString());
    }
}
