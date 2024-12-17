package com.RestaurantReservation.applicartion.usecases;


import com.RestaurantReservation.application.usecases.GetReviewsByRestaurantIdUseCase;
import com.RestaurantReservation.domain.entities.Review;
import com.RestaurantReservation.infra.repositories.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GetReviewsByRestaurantIdUseCaseTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private GetReviewsByRestaurantIdUseCase getReviewsByRestaurantIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_ShouldReturnReviews_WhenRepositoryFindsResults() {
        // Arrange
        UUID restaurantId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");

        Review review1 = new Review();
        review1.setId(UUID.fromString("223e4567-e89b-12d3-a456-426614174001"));
        review1.setRestaurantId(restaurantId);
        review1.setScore(4);
        review1.setComment("Great food!");

        Review review2 = new Review();
        review2.setId(UUID.fromString("323e4567-e89b-12d3-a456-426614174002"));
        review2.setRestaurantId(restaurantId);
        review2.setScore(5);
        review2.setComment("Otimo!");

        List<Review> reviews = List.of(review1, review2);

        when(reviewRepository.findByRestaurantId(restaurantId)).thenReturn(reviews);

        // Act
        List<Review> result = getReviewsByRestaurantIdUseCase.execute(restaurantId);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Great food!", result.get(0).getComment());
        assertEquals("Otimo!", result.get(1).getComment());

        verify(reviewRepository, times(1)).findByRestaurantId(restaurantId);
    }

    @Test
    void testExecute_ShouldReturnEmptyList_WhenRepositoryFindsNoResults() {
        // Arrange
        UUID restaurantId = UUID.fromString("423e4567-e89b-12d3-a456-426614174003");

        when(reviewRepository.findByRestaurantId(restaurantId)).thenReturn(List.of());

        // Act
        List<Review> result = getReviewsByRestaurantIdUseCase.execute(restaurantId);

        // Assert
        assertEquals(0, result.size());

        verify(reviewRepository, times(1)).findByRestaurantId(restaurantId);
    }

    @Test
    void testExecute_ShouldThrowException_WhenRepositoryThrowsException() {
        // Arrange
        UUID restaurantId = UUID.fromString("523e4567-e89b-12d3-a456-426614174004");

        when(reviewRepository.findByRestaurantId(restaurantId))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException exception =
                org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
                    getReviewsByRestaurantIdUseCase.execute(restaurantId);
                });

        assertEquals("Database error", exception.getMessage());

        verify(reviewRepository, times(1)).findByRestaurantId(restaurantId);
    }
}
