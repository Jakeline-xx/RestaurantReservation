package com.RestaurantReservation.applicartion.controllers;

import com.RestaurantReservation.application.dtos.ReservationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldRegisterReservation() throws Exception {
        var dto = new ReservationDTO();
        dto.setRestaurantId(UUID.randomUUID());
        dto.setCustomerName("Jakeline");
        dto.setDateTime("2024-12-20T19:00");
        dto.setNumberOfPeople(4);

        mockMvc.perform(post("/reserves/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Jakeline"));
    }

    @Test
    void shouldGetReservationsByRestaurantId() throws Exception {
        var restaurantId = UUID.randomUUID();

        mockMvc.perform(get("/reserves/get")
                        .param("restaurantId", restaurantId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
