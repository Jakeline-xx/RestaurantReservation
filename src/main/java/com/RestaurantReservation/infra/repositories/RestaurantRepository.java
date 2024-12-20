package com.RestaurantReservation.infra.repositories;


import com.RestaurantReservation.domain.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
    List<Restaurant> findByNameOrLocationOrTypeOfKitchen(String name, String location, String typeOfKitchen);
}