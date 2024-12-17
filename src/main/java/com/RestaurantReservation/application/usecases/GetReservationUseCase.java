package com.RestaurantReservation.application.usecases;

import com.RestaurantReservation.domain.entities.Reservation;
import com.RestaurantReservation.infra.repository.ReservationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GetReservationUseCase {
    private final ReservationRepository reservationRepository;

    public GetReservationUseCase(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> execute(UUID restaurantId) {
        return reservationRepository.findByRestaurantId(restaurantId);
    }
}
