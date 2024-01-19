package com.claims.manage.controller;

import com.claims.manage.controller.InsurancePoliciesController;
import com.claims.manage.domain.InsurancePolicies;
import com.claims.manage.exception.NotFoundException;
import com.claims.manage.exception.ResourceNotFoundException;
import com.claims.manage.service.InsurancePoliciesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class InsurancePoliciesControllerTest {
    @Mock
    private InsurancePoliciesService insurancePoliciesService;

    @InjectMocks
    private InsurancePoliciesController insurancePoliciesController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllInsurancePolicies() {
        InsurancePolicies policy1 = new InsurancePolicies(); // Set up your InsurancePolicies objects as needed
        InsurancePolicies policy2 = new InsurancePolicies();
        List<InsurancePolicies> policies = Arrays.asList(policy1, policy2);
        when(insurancePoliciesService.getAllInsurancePolicies()).thenReturn(policies);

        ResponseEntity<?> response = insurancePoliciesController.getAllInsurancePolicies();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(policies, response.getBody());
    }

    @Test
    public void testGetAllInsurancePoliciesThrowsResourceNotFoundException() {
        when(insurancePoliciesService.getAllInsurancePolicies()).thenThrow(new ResourceNotFoundException("No insurances found"));

        ResponseEntity<?> response = insurancePoliciesController.getAllInsurancePolicies();

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No insurances found", response.getBody());
    }

    @Test
    public void testGetInsurancePolicyById() throws NotFoundException {
        String id = "testId";
        InsurancePolicies policy = new InsurancePolicies(); // Set up your InsurancePolicies object as needed
        when(insurancePoliciesService.getById(id)).thenReturn(policy);

        ResponseEntity<?> response = insurancePoliciesController.getInsurancePolicyById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(policy, response.getBody());
    }

    @Test
    public void testGetInsurancePolicyByIdThrowsNotFoundException() throws NotFoundException {
        String id = "testId";
        when(insurancePoliciesService.getById(id)).thenThrow(new NotFoundException("No insurance found with id: " + id));

        ResponseEntity<?> response = insurancePoliciesController.getInsurancePolicyById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No insurance found with id: " + id, response.getBody());
    }
}
