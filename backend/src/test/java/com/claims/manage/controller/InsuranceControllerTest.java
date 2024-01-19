package com.claims.manage.controller;

import com.claims.manage.controller.InsuranceController;
import com.claims.manage.domain.Insurance;
import com.claims.manage.exception.NotFoundException;
import com.claims.manage.service.InsuranceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class InsuranceControllerTest {

    @Mock
    private InsuranceService insuranceService;

    @InjectMocks
    private InsuranceController insuranceController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuyInsurance() throws Exception {
        Insurance insurance = new Insurance(); // Set up your Insurance object as needed
        when(insuranceService.buyInsurance(insurance)).thenReturn(insurance);

        ResponseEntity<?> response = insuranceController.buyInsurance(insurance);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(insurance, response.getBody());
    }

    @Test
    public void testBuyInsuranceThrowsException() throws Exception {
        Insurance insurance = new Insurance();
        when(insuranceService.buyInsurance(insurance)).thenThrow(new RuntimeException("Error occurred"));

        ResponseEntity<?> response = insuranceController.buyInsurance(insurance);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error occurred", response.getBody());
    }
    @Test
    public void testGetInsurancesByUsername() throws NotFoundException {
        String username = "testUser";
        Insurance insurance = new Insurance(); // Set up your Insurance object as needed
        List<Insurance> insurances = Collections.singletonList(insurance);
        when(insuranceService.getByUsername(username)).thenReturn(insurances);

        ResponseEntity<?> response = insuranceController.getInsurancesByUsername(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(insurances, response.getBody());
    }

    @Test
    public void testGetInsurancesByUsernameThrowsNotFoundException() throws NotFoundException {
        String username = "testUser";
        when(insuranceService.getByUsername(username)).thenThrow(new NotFoundException("No insurance found for username: " + username));

        ResponseEntity<?> response = insuranceController.getInsurancesByUsername(username);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No insurance found for username: " + username, response.getBody());
    }

    @Test
    public void testGetInsurancesById() throws NotFoundException {
        String id = "testId";
        Insurance insurance = new Insurance(); // Set up your Insurance object as needed
        when(insuranceService.getById(id)).thenReturn(insurance);

        ResponseEntity<?> response = insuranceController.getInsurancesById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(insurance, response.getBody());
    }

    @Test
    public void testGetInsurancesByIdThrowsNotFoundException() throws NotFoundException {
        String id = "testId";
        when(insuranceService.getById(id)).thenThrow(new NotFoundException("No insurance found with id: " + id));

        ResponseEntity<?> response = insuranceController.getInsurancesById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No insurance found with id: " + id, response.getBody());
    }
}