package com.RestaurantReservation.applicartion.usecases;


import com.RestaurantReservation.application.usecases.GetReservationUseCase;
import com.RestaurantReservation.domain.entities.Reservation;
import com.RestaurantReservation.infra.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetReservationUseCaseTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private GetReservationUseCase getReservationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_ShouldReturnReservations_WhenRepositoryFindsResults() {
        // Arrange
        UUID restaurantId = UUID.randomUUID();

        Reservation reservation1 = new Reservation();
        reservation1.setId(UUID.randomUUID());
        reservation1.setRestaurantId(restaurantId);
        reservation1.setCustomerName("Lucas");

        Reservation reservation2 = new Reservation();
        reservation2.setId(UUID.randomUUID());
        reservation2.setRestaurantId(restaurantId);
        reservation2.setCustomerName("Ana");

        List<Reservation> reservations = List.of(reservation1, reservation2);

        when(reservationRepository.findByRestaurantId(restaurantId)).thenReturn(reservations);

        // Act
        List<Reservation> result = getReservationUseCase.execute(restaurantId);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Lucas", result.get(0).getCustomerName());
        assertEquals("Ana", result.get(1).getCustomerName());

        verify(reservationRepository, times(1)).findByRestaurantId(restaurantId);
    }

    @Test
    void testExecute_ShouldReturnEmptyList_WhenRepositoryFindsNoResults() {
        // Arrange
        UUID restaurantId = UUID.randomUUID();
        when(reservationRepository.findByRestaurantId(restaurantId)).thenReturn(List.of());

        // Act
        List<Reservation> result = getReservationUseCase.execute(restaurantId);

        // Assert
        assertEquals(0, result.size());
        verify(reservationRepository, times(1)).findByRestaurantId(restaurantId);
    }

    @Test
    void testExecute_ShouldThrowException_WhenRepositoryThrowsException() {
        // Arrange
        UUID restaurantId = UUID.randomUUID();
        when(reservationRepository.findByRestaurantId(restaurantId))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException exception =
                org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
                    getReservationUseCase.execute(restaurantId);
                });

        assertEquals("Database error", exception.getMessage());
        verify(reservationRepository, times(1)).findByRestaurantId(restaurantId);
    }
}
