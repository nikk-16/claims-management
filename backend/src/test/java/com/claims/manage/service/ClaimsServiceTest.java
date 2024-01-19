package com.claims.manage.service;

import com.claims.manage.domain.Claims;
import com.claims.manage.domain.InsurancePolicies;
import com.claims.manage.exception.NotFoundException;
import com.claims.manage.model.ApplyClaims;
import com.claims.manage.repository.ClaimsRepository;
import com.claims.manage.repository.InsurancePoliciesRepository;
import com.claims.manage.repository.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.claims.manage.exception.ResourceNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClaimsServiceTest {
    private ClaimsService claimsService;
    @Mock
    private ClaimsRepository claimsRepository;
    @Mock
    private UsersRepository usersRepository;

    @Before
    public void setup() {
        claimsService = Mockito.mock(ClaimsService.class);
        claimsRepository = Mockito.mock(ClaimsRepository.class);
        usersRepository = Mockito.mock(UsersRepository.class);
        this.claimsService = new ClaimsService(this.claimsRepository, this.usersRepository);
    }

    @Test
    public void apply_ClaimIsNotNull_ReturnsClaim() {
        // given
        ApplyClaims applyClaims = new ApplyClaims();
        applyClaims.setName("testName");
        applyClaims.setInsuranceType("testInsuranceType");
        applyClaims.setClaimReason("testClaimReason");
        applyClaims.setEstimatedAmount(100.00);

        Claims expectedClaim = new Claims("testName", "testInsuranceType", "testClaimReason", 100.00);
        expectedClaim.setStatus("Applied");

        when(claimsRepository.save(Mockito.any(Claims.class))).thenReturn(expectedClaim);

        // when
        Claims actualClaim = claimsService.apply(applyClaims);

        // then
        assertEquals(expectedClaim, actualClaim);
    }

    @Test
    public void apply_ClaimIsNull_ThrowsIllegalArgumentException() {
        // given
        ApplyClaims applyClaims = null;

        // when + then
        assertThrows(IllegalArgumentException.class, () -> claimsService.apply(applyClaims));
    }
}
