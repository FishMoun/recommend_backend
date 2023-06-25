package com.hhu.controller;

import com.hhu.entity.Rating;
import com.hhu.service.RateService;
import com.hhu.utils.BaseResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RateControllerTest {
//    @Autowired
//    private MockMvc mockMvc;

    @MockBean
    private RateService rateService;
    @Test
    void rate() {

    }

    @Test
    void getRatedMovie() {
    }

    @Test
    void getMovieRateByUserId() {
    }
}