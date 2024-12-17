package com.RestaurantReservation.applicartion.controllers;


import com.RestaurantReservation.application.dtos.RestaurantDTO;
import com.RestaurantReservation.application.usecases.RegisterRestaurantUseCase;
import com.RestaurantReservation.application.usecases.GetRestaurantUseCase;
import com.RestaurantReservation.apresentation.controllers.RestaurantController;
import com.RestaurantReservation.domain.entities.Restaurant;
import com.RestaurantReservation.infra.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RegisterRestaurantUseCase registerUseCase;

    @Mock
    private GetRestaurantUseCase getUseCase;

    private RestaurantDTO restaurantDTO;

    @BeforeEach
    public void setup() {
        restaurantDTO = new RestaurantDTO("Restaurant A", "Location 1", "Italian");
    }

    @Test
    void testRegisterRestaurant() throws Exception {
        when(registerUseCase.execute(any(Restaurant.class))).thenReturn(new Restaurant("Restaurant A", "Location 1", "Italian"));

        mockMvc.perform(post("/restaurant/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Restaurant A\", \"location\":\"Location 1\", \"typeOfKitchen\":\"Italian\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Restaurant A"))
                .andExpect(jsonPath("$.location").value("Location 1"))
                .andExpect(jsonPath("$.typeOfKitchen").value("Italian"));
    }

    @Test
    void testGetRestaurant() throws Exception {
        when(getUseCase.execute("Restaurant A", null, null)).thenReturn(List.of(new Restaurant("Restaurant A", "Location 1", "Italian")));

        mockMvc.perform(get("/restaurant/get")
                        .param("name", "Restaurant A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Restaurant A"))
                .andExpect(jsonPath("$[0].location").value("Location 1"))
                .andExpect(jsonPath("$[0].typeOfKitchen").value("Italian"));
    }

    @Test
    void testGetRestaurantWithNoResults() throws Exception {
        when(getUseCase.execute("Nonexistent Restaurant", null, null)).thenReturn(List.of());

        mockMvc.perform(get("/restaurant/get")
                        .param("name", "Nonexistent Restaurant"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}
