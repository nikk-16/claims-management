package com.claims.manage.service;

import com.claims.manage.domain.Insurance;
import com.claims.manage.exception.NotFoundException;
import com.claims.manage.repository.InsuranceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;

    public Insurance buyInsurance(Insurance insurance) throws Exception {
        try {
            return insuranceRepository.save(insurance);
        } catch (Exception e){
            throw new Exception("Sorry! could not process");
        }
    }

    public List<Insurance> getByUsername(String username) throws NotFoundException {
        List<Insurance> insurances = insuranceRepository.findInsurancesByUsername(username);
        if (insurances.isEmpty()) {
            throw new NotFoundException("No insurances found for username: " + username);
        }
        return insurances;
    }

    public Insurance getById(String id) throws NotFoundException {
        return insuranceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Insurance not found with id: " + id));
    }
}
