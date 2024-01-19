package com.claims.manage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ManageApplicationTests {

    @Autowired
    private ModelMapper modelMapper;

    @SpyBean
    private ManageApplication manageApplication;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testMapperBean() {
        ModelMapper mapper = manageApplication.mapper();
        assertThat(mapper).isNotNull();
        assertThat(mapper).isSameAs(modelMapper);
    }
}










