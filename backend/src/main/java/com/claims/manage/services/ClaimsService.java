package com.claims.manage.services;

import com.claims.manage.components.ApplyClaims;
import com.claims.manage.models.Claims;
import com.claims.manage.repositories.ClaimsRepository;
import com.claims.manage.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClaimsService {
  private final ClaimsRepository claimsRepository;
  private final UsersRepository usersRepository;

  public Claims apply(ApplyClaims claim){
//    private String id;
//    private String insuranceId;
//    private String policyNo;

//    Claims claims = new Claims();
//    claims.setName(claim.getName());
//    claims.setType(claim.getInsuranceType());
//    claims.setReason(claim.getClaimReason());
//    claims.setAmount(claim.getEstimatedAmount());
//    claims.setStatus("Applied");
//      return claimsRepository.save(claims);
      System.out.println("Apllied");

    return Optional.ofNullable(claim)
            .map(c -> {
              Claims claims = new Claims(c.getName(), c.getInsuranceType(), c.getClaimReason(), c.getEstimatedAmount());
              claims.setStatus("Applied");
              return claimsRepository.save(claims);
            })
            .orElseThrow(() -> new IllegalArgumentException("Claim cannot be null"));

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
