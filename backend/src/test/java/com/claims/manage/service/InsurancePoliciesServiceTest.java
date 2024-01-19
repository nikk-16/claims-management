package com.claims.manage.service;

import com.claims.manage.domain.InsurancePolicies;
import com.claims.manage.exception.NotFoundException;
import com.claims.manage.repository.InsurancePoliciesRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InsurancePoliciesServiceTest {

    private InsurancePoliciesService insurancePoliciesService;
    @Mock
    private InsurancePoliciesRepository insurancePoliciesRepository;

    @Before
    public void setup() {
        insurancePoliciesService = Mockito.mock(InsurancePoliciesService.class);
        insurancePoliciesRepository = Mockito.mock(InsurancePoliciesRepository.class);
        this.insurancePoliciesService = new InsurancePoliciesService(this.insurancePoliciesRepository);
    }

    @Test
    public void getAllInsurancePolicies_PoliciesExist_ReturnsPolicyList() {
        InsurancePolicies policy1 = new InsurancePolicies();
        InsurancePolicies policy2 = new InsurancePolicies();
        List<InsurancePolicies> expectedPolicies = Arrays.asList(policy1, policy2);
        when(insurancePoliciesRepository.findAll()).thenReturn(expectedPolicies);

        List<InsurancePolicies> actualPolicies = insurancePoliciesService.getAllInsurancePolicies();

        assertEquals(expectedPolicies, actualPolicies);
    }

    @Test
    public void getAllInsurancePolicies_NoPoliciesExist_ThrowsResourceNotFoundException() {
        when(insurancePoliciesRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> insurancePoliciesService.getAllInsurancePolicies());
    }

    @Test
    public void getById_PolicyExists_ReturnsPolicy() throws NotFoundException {
        InsurancePolicies expectedPolicy = new InsurancePolicies();
        String id = "testId";
        when(insurancePoliciesRepository.findById(id)).thenReturn(Optional.of(expectedPolicy));

        InsurancePolicies actualPolicy = insurancePoliciesService.getById(id);

        assertEquals(expectedPolicy, actualPolicy);
    }

    @Test
    public void getById_PolicyDoesNotExist_ThrowsNotFoundException() {
        String id = "testId";
        when(insurancePoliciesRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> insurancePoliciesService.getById(id));
    }
}
