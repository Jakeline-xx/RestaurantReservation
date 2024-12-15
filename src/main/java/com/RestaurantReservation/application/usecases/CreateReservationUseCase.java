package com.RestaurantReservation.application.usecases;

import com.RestaurantReservation.domain.entities.Reservation;
import com.RestaurantReservation.infra.repository.ReservationRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateReservationUseCase {
    private final ReservationRepository repository;

    public CreateReservationUseCase(ReservationRepository repository) {
        this.repository = repository;
    }

    public Reservation execute(Reservation reservation) {
        return repository.save(reservation);
    }
}