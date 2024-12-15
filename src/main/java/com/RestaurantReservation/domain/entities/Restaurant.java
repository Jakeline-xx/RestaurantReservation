package com.RestaurantReservation.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
public class Restaurant {
    @Id
    @UuidGenerator
    private UUID id;

    private String name;
    private String location;
    private String typeOfKitchen;
    private String openingHours;
    private int capacity;

}