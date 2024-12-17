package com.RestaurantReservation.application.usecases;

import com.RestaurantReservation.domain.entities.Review;
import com.RestaurantReservation.infra.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetReviewsByRestaurantIdUseCase {
    private final ReviewRepository reviewRepository;

    public GetReviewsByRestaurantIdUseCase(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> execute(UUID restaurantId) {
        return reviewRepository.findByRestaurantId(restaurantId);
    }
}
