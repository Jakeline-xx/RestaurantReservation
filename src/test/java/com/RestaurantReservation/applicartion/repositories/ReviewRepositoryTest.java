package com.RestaurantReservation.applicartion.repositories;


import com.RestaurantReservation.domain.entities.Restaurant;
import com.RestaurantReservation.domain.entities.Review;
import com.RestaurantReservation.infra.repositories.RestaurantRepository;
import com.RestaurantReservation.infra.repositories.ReviewRepository;
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
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private Restaurant restaurant1;
    private Restaurant restaurant2;
    private Review review1;
    private Review review2;

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

        restaurant1 = restaurantRepository.save(restaurant1);
        restaurant2 = restaurantRepository.save(restaurant2);

        review1 = new Review();
        review1.setRestaurantId(restaurant1.getId());
        review1.setScore(5);
        review1.setComment("Excellent food!");

        review2 = new Review();
        review2.setRestaurantId(restaurant1.getId());
        review2.setScore(4);
        review2.setComment("Very good, but could improve service.");

        reviewRepository.save(review1);
        reviewRepository.save(review2);
    }

    @Test
    void testFindByRestaurantId() {
        List<Review> reviews = reviewRepository.findByRestaurantId(restaurant1.getId());

        assertThat(reviews).hasSize(2);
        assertThat(reviews.get(0).getRestaurantId()).isEqualTo(restaurant1.getId());
        assertThat(reviews.get(1).getRestaurantId()).isEqualTo(restaurant1.getId());

        List<Review> noReviews = reviewRepository.findByRestaurantId(restaurant2.getId());

        assertThat(noReviews).isEmpty();
    }
}
