package com.claims.manage.services;

import com.claims.manage.models.Insurances;
import com.claims.manage.repositories.InsurancesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsurancesRepository insurancesRepository;

    public Insurances buyInsurance(Insurances insurance){
        return insurancesRepository.save(insurance);
    }

    public List<Insurances> getByUsername(String username) {
        System.out.println(insurancesRepository.findAll());
//        System.out.println(insuranceRepository.findByUsername(username));
        System.out.println(insurancesRepository.findInsurancesByUsername(username));
        return insurancesRepository.findInsurancesByUsername(username);
    }
}
