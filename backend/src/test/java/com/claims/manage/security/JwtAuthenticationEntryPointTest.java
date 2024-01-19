package com.claims.manage.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class JwtAuthenticationEntryPointTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUnauthorizedAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/some-secured-endpoint"))
                .andExpect(status().is(HttpStatus.UNAUTHORIZED.value()))
                .andExpect(content().string("Unauthorized access"));
    }
}
