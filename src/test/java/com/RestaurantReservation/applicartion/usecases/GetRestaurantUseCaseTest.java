package com.RestaurantReservation.applicartion.usecases;


import com.RestaurantReservation.application.usecases.GetRestaurantUseCase;
import com.RestaurantReservation.domain.entities.Restaurant;
import com.RestaurantReservation.infra.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetRestaurantUseCaseTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private GetRestaurantUseCase getRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_ShouldReturnRestaurants_WhenRepositoryFindsResults() {
        // Arrange
        String name = "Pizza Place";
        String location = "Downtown";
        String typeOfKitchen = "Italian";

        Restaurant restaurant1 = new Restaurant();
        restaurant1.setId(UUID.fromString("beba0b14-57d7-4527-a2eb-7716c759a4a5"));
        restaurant1.setName("Pizza Place");
        restaurant1.setLocation("Downtown");
        restaurant1.setTypeOfKitchen("Italian");

        Restaurant restaurant2 = new Restaurant();
        restaurant2.setId(UUID.fromString("beba0b14-57d7-4527-a2eb-7716c759a4a5"));
        restaurant2.setName("Pasta House");
        restaurant2.setLocation("Downtown");
        restaurant2.setTypeOfKitchen("Italian");

        List<Restaurant> restaurants = List.of(restaurant1, restaurant2);

        when(restaurantRepository.findByNameOrLocationOrTypeOfKitchen(name, location, typeOfKitchen))
                .thenReturn(restaurants);

        // Act
        List<Restaurant> result = getRestaurantUseCase.execute(name, location, typeOfKitchen);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Pizza Place", result.get(0).getName());
        assertEquals("Pasta House", result.get(1).getName());

        verify(restaurantRepository, times(1))
                .findByNameOrLocationOrTypeOfKitchen(name, location, typeOfKitchen);
    }

    @Test
    void testExecute_ShouldReturnEmptyList_WhenRepositoryFindsNoResults() {
        // Arrange
        String name = "Burger Joint";
        String location = "Uptown";
        String typeOfKitchen = "American";

        when(restaurantRepository.findByNameOrLocationOrTypeOfKitchen(name, location, typeOfKitchen))
                .thenReturn(List.of());

        // Act
        List<Restaurant> result = getRestaurantUseCase.execute(name, location, typeOfKitchen);

        // Assert
        assertEquals(0, result.size());

        verify(restaurantRepository, times(1))
                .findByNameOrLocationOrTypeOfKitchen(name, location, typeOfKitchen);
    }

    @Test
    void testExecute_ShouldThrowException_WhenRepositoryThrowsException() {
        // Arrange
        String name = "Sushi Place";
        String location = "Midtown";
        String typeOfKitchen = "Japanese";

        when(restaurantRepository.findByNameOrLocationOrTypeOfKitchen(name, location, typeOfKitchen))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException exception =
                org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
                    getRestaurantUseCase.execute(name, location, typeOfKitchen);
                });

        assertEquals("Database error", exception.getMessage());

        verify(restaurantRepository, times(1))
                .findByNameOrLocationOrTypeOfKitchen(name, location, typeOfKitchen);
    }
}
