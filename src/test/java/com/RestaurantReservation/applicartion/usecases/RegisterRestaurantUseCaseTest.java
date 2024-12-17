package com.RestaurantReservation.applicartion.usecases;


import com.RestaurantReservation.application.usecases.RegisterRestaurantUseCase;
import com.RestaurantReservation.domain.entities.Restaurant;
import com.RestaurantReservation.infra.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegisterRestaurantUseCaseTest {

    @Mock
    private RestaurantRepository repository;

    @InjectMocks
    private RegisterRestaurantUseCase registerRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_ShouldReturnSavedRestaurant_WhenRepositorySavesSuccessfully() {
        // Arrange
        Restaurant inputRestaurant = new Restaurant();
        inputRestaurant.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        inputRestaurant.setName("Paparoto");
        inputRestaurant.setLocation("JK");
        inputRestaurant.setTypeOfKitchen("Italian");

        Restaurant savedRestaurant = new Restaurant();
        savedRestaurant.setId(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"));
        savedRestaurant.setName("Paparoto");
        savedRestaurant.setLocation("JK");
        savedRestaurant.setTypeOfKitchen("Italian");

        when(repository.save(any(Restaurant.class))).thenReturn(savedRestaurant);

        // Act
        Restaurant result = registerRestaurantUseCase.execute(inputRestaurant);

        // Assert
        assertEquals(savedRestaurant.getId(), result.getId());
        assertEquals(savedRestaurant.getName(), result.getName());
        assertEquals(savedRestaurant.getLocation(), result.getLocation());
        assertEquals(savedRestaurant.getTypeOfKitchen(), result.getTypeOfKitchen());

        verify(repository, times(1)).save(inputRestaurant);
    }

    @Test
    void testExecute_ShouldThrowException_WhenRepositoryThrowsException() {
        // Arrange
        Restaurant inputRestaurant = new Restaurant();
        inputRestaurant.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174001"));
        inputRestaurant.setName("Grill House");

        when(repository.save(any(Restaurant.class)))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException exception =
                org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
                    registerRestaurantUseCase.execute(inputRestaurant);
                });

        assertEquals("Database error", exception.getMessage());
        verify(repository, times(1)).save(inputRestaurant);
    }
}
