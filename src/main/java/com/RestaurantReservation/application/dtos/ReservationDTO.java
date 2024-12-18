package com.RestaurantReservation.application.dtos;

import com.RestaurantReservation.domain.entities.Reservation;
import lombok.Data;

import java.util.UUID;

@Data
public class ReservationDTO {
    private UUID restaurantId;
    private String customerName;
    private String dateTime;
    private int numberOfPeople;

    public static ReservationDTO fromEntity(Reservation reserva) {
        var dto = new ReservationDTO();
        dto.setRestaurantId(reserva.getRestaurantId());
        dto.setCustomerName(reserva.getCustomerName());
        dto.setDateTime(reserva.getDateTime());
        dto.setNumberOfPeople(reserva.getNumberOfPeople());
        return dto;
    }

    public Reservation toEntity() {
        var reservation = new Reservation();
        reservation.setRestaurantId(restaurantId);
        reservation.setCustomerName(customerName);
        reservation.setDateTime(dateTime);
        reservation.setNumberOfPeople(numberOfPeople);
        return reservation;
    }
}


