package com.claims.manage.config;

import com.claims.manage.domain.Users;
import com.claims.manage.exception.ResourceNotFoundException;
import com.claims.manage.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UsersRepository usersRepository;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.usersRepository.findUsersByUsername(username).orElseThrow(() -> new ResourceNotFoundException("email doesn't exist"));
        return user;
    }
}
