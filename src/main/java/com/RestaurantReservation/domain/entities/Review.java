package com.RestaurantReservation.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
public class Review {
    @Id
    @UuidGenerator
    private UUID id;

    private UUID restaurantId;
    private String comment;
    private int score;
}