package com.RestaurantReservation.domain.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    @UuidGenerator
    private UUID id;

    private String name;
    private String location;
    private String typeOfKitchen;
    private String openingHours;
    private int capacity;

    public Restaurant(String name, String location, String typeOfKitchen) {
        this.name = name;
        this.location = location;
        this.typeOfKitchen = typeOfKitchen;
    }
}