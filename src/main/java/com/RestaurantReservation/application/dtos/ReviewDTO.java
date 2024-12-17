package com.RestaurantReservation.application.dtos;

import com.RestaurantReservation.domain.entities.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private UUID restaurantId;
    private String comment;
    private int score;

    public Review toEntity() {
        var review = new Review();
        review.setRestaurantId(restaurantId);
        review.setComment(comment);
        review.setScore(score);
        return review;
    }

    public static ReviewDTO fromEntity(Review review) {
        var dto = new ReviewDTO();
        dto.setRestaurantId(review.getRestaurantId());
        dto.setComment(review.getComment());
        dto.setScore(review.getScore());
        return dto;
    }
}
