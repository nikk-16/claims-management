package com.claims.manage.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // get token from header
        String requestToken = request.getHeader("Authorization");
        logger.info("message {}" , requestToken);

        String username = null;
        String jwtToken = null;

        if(requestToken != null && requestToken.trim().startsWith("Bearer")){
            // get Actual Token
            jwtToken = requestToken.substring(7);

            try{
                username = this.jwtHelper.getUsername(jwtToken);
            }
            catch(ExpiredJwtException e) {
                logger.info("Invalid token message", "Jwt Token Expired");
            }
            catch (MalformedJwtException e) {
                logger.info("Invalid token message", "Invalid Jwt token");
            }
            catch (IllegalArgumentException e) {
                logger.info("Invalid token message", "Unable to get token");
            }

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                // validate
                UserDetails newUserDetails = this.userDetailsService.loadUserByUsername(username);
                if(this.jwtHelper.validateToken(jwtToken, newUserDetails)){
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(newUserDetails, null, newUserDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
                else{
                    logger.info("Not Validate Message", "Invalid Jwt Token");
                }
            }
            else {
                logger.info("User Message", "Username is null or User already exists");
            }
        }
        else {
            logger.info("Token message {}", "token does not start with Bearer");
        }
        filterChain.doFilter(request, response);
    }
}
