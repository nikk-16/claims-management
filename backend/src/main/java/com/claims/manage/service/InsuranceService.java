package com.claims.manage.service;

import com.claims.manage.domain.Insurance;
import com.claims.manage.repository.InsuranceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;

    public Insurance buyInsurance(Insurance insurance){
        System.out.println(insurance);
        return insuranceRepository.save(insurance);
    }

    public List<Insurance> getByUsername(String username) {
        System.out.println(insuranceRepository.findAll());
//        System.out.println(insuranceRepository.findByUsername(username));
        System.out.println(insuranceRepository.findInsurancesByUsername(username));
        return insuranceRepository.findInsurancesByUsername(username);
    }
}
