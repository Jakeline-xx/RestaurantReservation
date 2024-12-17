package com.RestaurantReservation.apresentation.controllers;

import com.RestaurantReservation.application.dtos.ReservationDTO;
import com.RestaurantReservation.application.usecases.CreateReservationUseCase;
import com.RestaurantReservation.application.usecases.GetReservationUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reserves")
public class ReservationController {

    private final CreateReservationUseCase reservationUseCase;
    private final GetReservationUseCase getReservationUseCase;

    public ReservationController(CreateReservationUseCase reservationUseCase, GetReservationUseCase getReservationUseCase) {
        this.reservationUseCase = reservationUseCase;
        this.getReservationUseCase = getReservationUseCase;
    }

    @GetMapping("/get")
    public ResponseEntity<List<ReservationDTO>> getReservation(@RequestParam UUID restaurantId) {
        var reservations = getReservationUseCase.execute(restaurantId);
        var reservationDTOs = reservations.stream().map(ReservationDTO::fromEntity).toList();
        return ResponseEntity.ok(reservationDTOs);
    }

    @PostMapping("/register")
    public ResponseEntity<ReservationDTO> registerReservation(@RequestBody ReservationDTO dto) {
        var reservation = reservationUseCase.execute(dto.toEntity());
        return ResponseEntity.ok(ReservationDTO.fromEntity(reservation));
    }
}
