package com.RestaurantReservation.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Entity
public class Reservation {
    @Id
    @UuidGenerator
    private UUID id;

    private UUID restaurantId;
    private String customerName;
    private String dateTime;
    private int numberOfPeople;
}