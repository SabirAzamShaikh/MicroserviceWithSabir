package com.example.Hotel.controller;

import com.example.Hotel.entity.Hotel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

import java.sql.Timestamp;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class HotelControllerTest
{
    @Autowired
    private MockMvc mockmvc;
@Autowired
    private ObjectMapper mapper;
public static Hotel hotel;
@BeforeEach
public void sest()
{
    hotel=new Hotel();
    hotel.setHotelemailid("www.sabirazamshaikh313@gmail.com");
    hotel.setAddress("pune");
    hotel.setHotelid(1);
    hotel.setHotelname("shabnam");
    hotel.setPhonenumber("9822612861");
    hotel.setCreatedAt(new Timestamp(System.currentTimeMillis()));
    hotel.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
}

    @Test
public void HotelValidationTest_Success() throws Exception {
    mockmvc.perform(post("/hotel/create")
                    .contentType("application/json")
                    .content(mapper.writeValueAsString(hotel)))
            .andExpect(status().isOk());
}
@Test
    public void HotelValidationTest_InvalidEmail()throws Exception
{
    hotel.setHotelemailid("sabir123");
    mockmvc.perform(post("/hotel/create")
                    .contentType("application/json")
                    .content(mapper.writeValueAsString(hotel)))
            .andExpect(jsonPath("$.hotelemailid").value("please enter a valid email id"))
            .andExpect(status().isNotAcceptable());
}

    @Test
    public void HotelValidationTest_InvalidphonenumberLength()throws Exception
    {
        hotel.setPhonenumber("12345678");
        mockmvc.perform(post("/hotel/create")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(hotel)))
                .andExpect(jsonPath("$.phonenumber").value("phone number length should be 10-15"))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void HotelValidationTest_InvalidphonenumberContainsAlphabet()throws Exception
    {
        hotel.setPhonenumber("123456782@@@");
        mockmvc.perform(post("/hotel/create")
                        .contentType("application/json")
                        .content(mapper.writeValueAsString(hotel)))
                .andExpect(jsonPath("$.phonenumber").value("Only numbers are allowed"))
                .andExpect(status().isNotAcceptable());
    }






}
