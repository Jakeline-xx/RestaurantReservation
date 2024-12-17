package com.RestaurantReservation.applicartion.repositories;

import com.RestaurantReservation.domain.entities.Reservation;
import com.RestaurantReservation.infra.repositories.ReservationRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@Transactional
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void shouldFindReservationsByRestaurantId() {
        var restaurantId = UUID.randomUUID();
        var reservation = new Reservation();
        reservation.setRestaurantId(restaurantId);
        reservation.setCustomerName("Gabriel");
        reservation.setDateTime("2024-12-21T20:00");
        reservation.setNumberOfPeople(2);

        reservationRepository.save(reservation);

        var results = reservationRepository.findByRestaurantId(restaurantId);
        assertFalse(results.isEmpty());
    }
}
