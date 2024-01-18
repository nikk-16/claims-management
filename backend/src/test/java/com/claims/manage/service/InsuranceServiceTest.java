package com.claims.manage.service;

import com.claims.manage.domain.Insurance;
import com.claims.manage.domain.InsurancePolicies;
import com.claims.manage.exception.NotFoundException;
import com.claims.manage.repository.InsurancePoliciesRepository;
import com.claims.manage.repository.InsuranceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.claims.manage.exception.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InsuranceServiceTest {
    private InsuranceService insuranceService;
    @Mock
    private InsuranceRepository insuranceRepository;

    @Before
    public void setup() {
        insuranceService = Mockito.mock(InsuranceService.class);
        insuranceRepository = Mockito.mock(InsuranceRepository.class);
        this.insuranceService = new InsuranceService(this.insuranceRepository);
    }

    @Test
    public void buyInsurance_InsuranceDoesNotExist_ReturnsInsurance() throws Exception {
        Insurance expectedInsurance = new Insurance();
        when(insuranceRepository.save(any(Insurance.class))).thenReturn(expectedInsurance);

        Insurance actualInsurance = insuranceService.buyInsurance(new Insurance());

        assertEquals(expectedInsurance, actualInsurance);
    }

    @Test
    public void buyInsurance_InsuranceExists_ThrowsException() {
        when(insuranceRepository.save(any(Insurance.class))).thenThrow(new DataIntegrityViolationException(""));

        assertThrows(Exception.class, () -> insuranceService.buyInsurance(new Insurance()));
    }

    @Test
    public void getByUsername_InsurancesExist_ReturnsInsuranceList() throws NotFoundException {
        Insurance insurance1 = new Insurance();
        Insurance insurance2 = new Insurance();
        List<Insurance> expectedInsurances = Arrays.asList(insurance1, insurance2);
        when(insuranceRepository.findInsurancesByUsername(anyString())).thenReturn(expectedInsurances);

        List<Insurance> actualInsurances = insuranceService.getByUsername("testUsername");

        assertEquals(expectedInsurances, actualInsurances);
    }

    @Test
    public void getByUsername_NoInsurancesExist_ThrowsNotFoundException() {
        when(insuranceRepository.findInsurancesByUsername(anyString())).thenReturn(Collections.emptyList());

        assertThrows(NotFoundException.class, () -> insuranceService.getByUsername("testUsername"));
    }

    @Test
    public void getById_InsuranceExists_ReturnsInsurance() throws NotFoundException {
        Insurance expectedInsurance = new Insurance();
        when(insuranceRepository.findById(anyString())).thenReturn(Optional.of(expectedInsurance));

        Insurance actualInsurance = insuranceService.getById("testId");

        assertEquals(expectedInsurance, actualInsurance);
    }

    @Test
    public void getById_InsuranceDoesNotExist_ThrowsNotFoundException() {
        when(insuranceRepository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> insuranceService.getById("testId"));
    }
}
