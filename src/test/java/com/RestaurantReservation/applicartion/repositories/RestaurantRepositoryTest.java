package com.RestaurantReservation.applicartion.repositories;


import com.RestaurantReservation.domain.entities.Restaurant;
import com.RestaurantReservation.infra.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RestaurantRepositoryTest {

    @Autowired
    private RestaurantRepository restaurantRepository;

    private Restaurant restaurant1;
    private Restaurant restaurant2;

    @BeforeEach
    void setUp() {
        restaurant1 = new Restaurant();
        restaurant1.setName("Restaurant A");
        restaurant1.setLocation("Location 1");
        restaurant1.setTypeOfKitchen("Italian");

        restaurant2 = new Restaurant();
        restaurant2.setName("Restaurant B");
        restaurant2.setLocation("Location 2");
        restaurant2.setTypeOfKitchen("Mexican");

        restaurantRepository.save(restaurant1);
        restaurantRepository.save(restaurant2);
    }

    @Test
    void testFindByNameOrLocationOrTypeOfKitchen() {
        List<Restaurant> foundRestaurants = restaurantRepository.findByNameOrLocationOrTypeOfKitchen("Restaurant A", null, null);

        assertThat(foundRestaurants).hasSize(1);
        assertThat(foundRestaurants.get(0).getName()).isEqualTo("Restaurant A");

        foundRestaurants = restaurantRepository.findByNameOrLocationOrTypeOfKitchen(null, "Location 2", null);
        assertThat(foundRestaurants).hasSize(1);
        assertThat(foundRestaurants.get(0).getLocation()).isEqualTo("Location 2");

        foundRestaurants = restaurantRepository.findByNameOrLocationOrTypeOfKitchen(null, null, "Mexican");
        assertThat(foundRestaurants).hasSize(1);
        assertThat(foundRestaurants.get(0).getTypeOfKitchen()).isEqualTo("Mexican");
    }
}
