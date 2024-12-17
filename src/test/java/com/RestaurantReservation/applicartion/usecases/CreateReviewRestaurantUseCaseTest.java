package com.RestaurantReservation.applicartion.usecases;

import com.RestaurantReservation.application.usecases.CreateReviewRestaurantUseCase;
import com.RestaurantReservation.domain.entities.Review;
import com.RestaurantReservation.infra.repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateReviewRestaurantUseCaseTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private CreateReviewRestaurantUseCase createReviewRestaurantUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute_ShouldReturnSavedReview_WhenRepositorySavesSuccessfully() {
        // Arrange
        Review inputReview = new Review();
        inputReview.setId(UUID.randomUUID());
        inputReview.setRestaurantId(UUID.randomUUID());
        inputReview.setScore(5);
        inputReview.setComment("Muito bom!");

        Review savedReview = new Review();
        savedReview.setId(inputReview.getId());
        savedReview.setRestaurantId(inputReview.getRestaurantId());
        savedReview.setScore(5);
        savedReview.setComment("Muito bom!");

        when(reviewRepository.save(any(Review.class))).thenReturn(savedReview);

        // Act
        Review result = createReviewRestaurantUseCase.execute(inputReview);

        // Assert
        assertEquals(savedReview.getId(), result.getId());
        assertEquals(savedReview.getRestaurantId(), result.getRestaurantId());
        assertEquals(savedReview.getScore(), result.getScore());
        assertEquals(savedReview.getComment(), result.getComment());

        verify(reviewRepository, times(1)).save(inputReview);
    }

    @Test
    void testExecute_ShouldThrowException_WhenRepositoryFails() {
        // Arrange
        Review inputReview = new Review();
        inputReview.setId(UUID.randomUUID());
        inputReview.setRestaurantId(UUID.randomUUID());
        inputReview.setScore(3);
        inputReview.setComment("Legal mas poderia melhorar.");

        when(reviewRepository.save(any(Review.class)))
                .thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException exception =
                org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
                    createReviewRestaurantUseCase.execute(inputReview);
                });

        assertEquals("Database error", exception.getMessage());

        verify(reviewRepository, times(1)).save(inputReview);
    }
}
