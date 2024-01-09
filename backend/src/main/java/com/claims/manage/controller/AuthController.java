package com.claims.manage.controller;


import com.claims.manage.Security.JwtHelper;
import com.claims.manage.domain.Users;
import com.claims.manage.exception.ResourceNotFoundException;
import com.claims.manage.model.JwtRequest;
import com.claims.manage.model.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final ModelMapper modelMapper;
    private final JwtHelper jwtHelper;

    @GetMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
        System.out.println("**"+request);
        this.authenticateUser(request.getUsername(), request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.jwtHelper.generatedToken(userDetails);
        JwtResponse response = new JwtResponse();
        response.setToken(token);
        response.setUser(this.modelMapper.map(userDetails, Users.class));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticateUser(String Username, String Password){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(Username, Password));
        }
        catch(BadCredentialsException e){
            throw new ResourceNotFoundException("Invalid username or password");
        }
        catch (DisabledException e){
            throw new ResourceNotFoundException("User is not active");
        }
    }

}
