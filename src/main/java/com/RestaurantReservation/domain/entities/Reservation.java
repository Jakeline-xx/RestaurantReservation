package com.RestaurantReservation.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class Reservation {
    @Id
    @UuidGenerator
    private UUID id;

    private Long restaurantId;
    private String customerName;
    private LocalDateTime dateTime;
    private int numberOfPeople;
}