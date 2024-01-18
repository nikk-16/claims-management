package com.claims.manage.service;

import com.claims.manage.model.ApplyClaims;
import com.claims.manage.domain.Claims;
import com.claims.manage.repository.ClaimsRepository;
import com.claims.manage.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClaimsService {
  private final ClaimsRepository claimsRepository;
  private final UsersRepository usersRepository;

  public Claims apply(ApplyClaims claim) throws IllegalArgumentException{

    return Optional.ofNullable(claim)
            .map(c -> {
              Claims claims = new Claims(c.getName(), c.getInsuranceType(), c.getClaimReason(), c.getEstimatedAmount());
              claims.setStatus("Applied");
              return claimsRepository.save(claims);
            })
            .orElseThrow(() -> new IllegalArgumentException("Details not complete"));

//      return Optional.ofNullable(claim)
//              .map(c -> {
//                  System.out.println("Claim details: " + c.toString()); // Add a toString method in claim class
//                  Claims claims = new Claims(c.getName(), c.getInsuranceType(), c.getClaimReason(), c.getEstimatedAmount());
//                  claims.setStatus("Applied");
//                  System.out.println("Saving claim: " + claims.toString()); // And also in Claims class
//                  return claimsRepository.save(claims);
//              })
//              .orElseThrow(() -> new IllegalArgumentException("Claim cannot be null"));

  }
}
