package com.RestaurantReservation.applicartion.usecases;

import com.RestaurantReservation.application.usecases.CreateReservationUseCase;
import com.RestaurantReservation.domain.entities.Reservation;
import com.RestaurantReservation.infra.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateReservationUseCaseTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private CreateReservationUseCase createReservationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_ShouldReturnSavedReservation_WhenRepositorySavesSuccessfully() {
        // Arrange
        Reservation inputReservation = new Reservation();
        inputReservation.setId(UUID.fromString("beba0b14-57d7-4527-a2eb-7716c759a4a5"));
        inputReservation.setCustomerName("Jakeline");

        Reservation savedReservation = new Reservation();
        savedReservation.setId(UUID.fromString("beba0b14-57d7-4527-a2eb-7716c759a4a5"));
        savedReservation.setCustomerName("Jakeline");

        when(reservationRepository.save(any(Reservation.class))).thenReturn(savedReservation);

        // Act
        Reservation result = createReservationUseCase.execute(inputReservation);

        // Assert
        assertEquals(savedReservation.getId(), result.getId());
        assertEquals(savedReservation.getCustomerName(), result.getCustomerName());

        verify(reservationRepository, times(1)).save(inputReservation);
    }

    @Test
    void testExecute_ShouldThrowException_WhenRepositoryThrowsException() {
        // Arrange
        Reservation inputReservation = new Reservation();
        inputReservation.setId(UUID.fromString("beba0b14-57d7-4527-a2eb-7716c759a4a5"));
        inputReservation.setCustomerName("Gabriel");

        when(reservationRepository.save(any(Reservation.class)))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            createReservationUseCase.execute(inputReservation);
        });

        assertEquals("Database error", exception.getMessage());
        verify(reservationRepository, times(1)).save(inputReservation);
    }
}
