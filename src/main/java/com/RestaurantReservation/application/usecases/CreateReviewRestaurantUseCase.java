package com.RestaurantReservation.application.usecases;

import com.RestaurantReservation.domain.entities.Review;
import com.RestaurantReservation.infra.repositories.ReviewRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateReviewRestaurantUseCase {
    private final ReviewRepository repository;

    public CreateReviewRestaurantUseCase(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review execute(Review review) {
        return repository.save(review);
    }
}