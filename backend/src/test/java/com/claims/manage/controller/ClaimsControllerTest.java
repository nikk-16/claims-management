package com.claims.manage.controller;

import com.claims.manage.domain.Claims;
import com.claims.manage.model.ApplyClaims;
import com.claims.manage.service.ClaimsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ClaimsControllerTest {

    @Mock
    private ClaimsService claimsService;

    @InjectMocks
    private ClaimsController claimsController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testApplyForClaims() {
        ApplyClaims claim = new ApplyClaims(); // Set up your claim object as needed
        Claims responseMessage = new Claims();
        when(claimsService.apply(claim)).thenReturn(responseMessage);

        ResponseEntity<?> response = claimsController.applyForClaims(claim);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseMessage, response.getBody());
    }

    @Test
    public void testApplyForClaimsWithIllegalArgumentException() {
        ApplyClaims claim = new ApplyClaims(); // Set up your claim object as needed
        String exceptionMessage = "Invalid claim";
        when(claimsService.apply(claim)).thenThrow(new IllegalArgumentException(exceptionMessage));

        ResponseEntity<?> response = claimsController.applyForClaims(claim);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(exceptionMessage, response.getBody());
    }

}
