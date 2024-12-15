package com.RestaurantReservation.application.dtos;

import com.RestaurantReservation.domain.entities.Restaurant;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RestaurantDTO {
    private String name;
    private String location;
    private String typeOfKitchen;
    private String openingHours;
    private int capacity;

    public Restaurant toEntity() {
        var restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setLocation(location);
        restaurant.setTypeOfKitchen(typeOfKitchen);
        restaurant.setOpeningHours(openingHours);
        restaurant.setCapacity(capacity);
        return restaurant;
    }

    public static RestaurantDTO fromEntity(Restaurant restaurant) {
        var dto = new RestaurantDTO();
        dto.setName(restaurant.getName());
        dto.setLocation(restaurant.getLocation());
        dto.setTypeOfKitchen(restaurant.getTypeOfKitchen());
        dto.setOpeningHours(restaurant.getOpeningHours());
        dto.setCapacity(restaurant.getCapacity());
        return dto;
    }
}