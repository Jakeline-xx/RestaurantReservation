package com.RestaurantReservation.application.usecases;

import com.RestaurantReservation.domain.entities.Restaurant;
import com.RestaurantReservation.infra.repositories.RestaurantRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetRestaurantUseCase {
    private final RestaurantRepository repository;

    public GetRestaurantUseCase(RestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> execute(String name, String location, String typeOfKitchen) {
        return repository.findByNameOrLocationOrTypeOfKitchen(name, location, typeOfKitchen);
    }
}