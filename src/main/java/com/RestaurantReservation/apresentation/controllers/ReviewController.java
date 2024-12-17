package com.RestaurantReservation.apresentation.controllers;


import com.RestaurantReservation.application.dtos.ReviewDTO;
import com.RestaurantReservation.application.usecases.CreateReviewRestaurantUseCase;
import com.RestaurantReservation.application.usecases.GetReviewsByRestaurantIdUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final CreateReviewRestaurantUseCase createReviewRestaurantUseCase;
    private final GetReviewsByRestaurantIdUseCase getReviewsByRestaurantIdUseCase;


    public ReviewController(CreateReviewRestaurantUseCase createReviewRestaurantUseCase, GetReviewsByRestaurantIdUseCase getReviewsByRestaurantIdUseCase) {
        this.createReviewRestaurantUseCase = createReviewRestaurantUseCase;
        this.getReviewsByRestaurantIdUseCase = getReviewsByRestaurantIdUseCase;
    }

    @GetMapping("/by-restaurant")
    public ResponseEntity<List<ReviewDTO>> getByRestaurantId(@RequestParam UUID restaurantId) {
        var reviews = getReviewsByRestaurantIdUseCase.execute(restaurantId);
        var reviewDTOs = reviews.stream().map(ReviewDTO::fromEntity).toList();
        return ResponseEntity.ok(reviewDTOs);
    }



    @PostMapping
    public ResponseEntity<ReviewDTO> reviewRestaurant(@RequestBody ReviewDTO dto) {
        var review = createReviewRestaurantUseCase.execute(dto.toEntity());
        return ResponseEntity.ok(ReviewDTO.fromEntity(review));
    }
}
