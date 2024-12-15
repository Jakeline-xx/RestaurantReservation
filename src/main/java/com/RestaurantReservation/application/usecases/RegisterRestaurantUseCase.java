package com.RestaurantReservation.application.usecases;

import com.RestaurantReservation.domain.entities.Restaurant;
import com.RestaurantReservation.infra.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

@Component
public class RegisterRestaurantUseCase {
    private final RestaurantRepository repository;

    public RegisterRestaurantUseCase(RestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant execute(Restaurant restaurant) {
        return repository.save(restaurant);
    }
}
