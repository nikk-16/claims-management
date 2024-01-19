package com.claims.manage.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class JwtAuthenticationFilterTest {
    @Mock
    private JwtHelper jwtHelper;
    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private Logger logger;
    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoFilterInternal() throws Exception {
        String requestToken = "Bearer testToken";
        String jwtToken = "testToken";
        String username = "testUser";

        when(request.getHeader("Authorization")).thenReturn(requestToken);
        when(jwtHelper.getUsername(jwtToken)).thenReturn(username);
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
        when(jwtHelper.validateToken(jwtToken, userDetails)).thenReturn(true);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
//        when(jwtHelper.validateToken(jwtToken, userDetails)).thenReturn(false);
//        verify(logger, times(1)).info("Not Validate Message", "Invalid Jwt Token");
        verify(filterChain, times(1)).doFilter(request, response);
        verify(logger, times(1)).info("message {}", requestToken);
    }

    @Test
    public void testDoFilterInternalWithValidUsernameAndNullAuthentication() throws Exception {
        String requestToken = "Bearer testToken";
        String jwtToken = "testToken";
        String username = "testUser";

        when(request.getHeader("Authorization")).thenReturn(requestToken);
        when(jwtHelper.getUsername(jwtToken)).thenReturn(username);
        when(jwtHelper.validateToken(jwtToken, any())).thenReturn(true);
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        verify(logger, never()).info("Not Validate Message", "Invalid Jwt Token");
//        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    public void testDoFilterInternalWithInvalidToken() throws Exception {
        String requestToken = "Bearer testToken";
        String jwtToken = "testToken";
        String username = "testUser";

        when(request.getHeader("Authorization")).thenReturn(requestToken);
        when(jwtHelper.getUsername(jwtToken)).thenReturn(username);
        when(jwtHelper.validateToken(jwtToken, any())).thenReturn(false);
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        verify(logger, times(1)).info("Not Validate Message", "Invalid Jwt Token");
//        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    public void testDoFilterInternalWithExpiredJwtException() throws Exception {
        String requestToken = "Bearer testToken";
        String jwtToken = "testToken";

        when(request.getHeader("Authorization")).thenReturn(requestToken);
        when(jwtHelper.getUsername(jwtToken)).thenThrow(ExpiredJwtException.class);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);

        verify(logger, times(1)).info("Invalid token message", "Jwt Token Expired");
    }

    @Test
    public void testDoFilterInternalWithMalformedJwtException() throws Exception {
        String requestToken = "Bearer testToken";
        String jwtToken = "testToken";

        when(request.getHeader("Authorization")).thenReturn(requestToken);
        when(jwtHelper.getUsername(jwtToken)).thenThrow(MalformedJwtException.class);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        verify(logger, times(1)).info("Invalid token message", "Invalid Jwt token");
    }

    @Test
    public void testDoFilterInternalWithIllegalArgumentException() throws Exception {
        String requestToken = "Bearer testToken";
        String jwtToken = "testToken";

        when(request.getHeader("Authorization")).thenReturn(requestToken);
        when(jwtHelper.getUsername(jwtToken)).thenThrow(IllegalArgumentException.class);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        verify(logger, times(1)).info("Invalid token message", "Unable to get token");
    }
}