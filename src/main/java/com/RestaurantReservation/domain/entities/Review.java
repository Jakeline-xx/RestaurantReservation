package com.RestaurantReservation.domain.entities;

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
public class Review {
    @Id
    @UuidGenerator
    private UUID id;

    private UUID restaurantId;
    private String comment;
    private int score;

    public Review(UUID restaurantId, String comment, int score) {
        this.restaurantId = restaurantId;
        this.comment = comment;
        this.score = score;
    }
}