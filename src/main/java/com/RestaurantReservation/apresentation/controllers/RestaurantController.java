package com.RestaurantReservation.apresentation.controllers;

import com.RestaurantReservation.application.dtos.RestaurantDTO;
import com.RestaurantReservation.application.usecases.RegisterRestaurantUseCase;
import com.RestaurantReservation.application.usecases.GetRestaurantUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RegisterRestaurantUseCase registerUseCase;
    private final GetRestaurantUseCase getUseCase;

    public RestaurantController(RegisterRestaurantUseCase registerUseCase, GetRestaurantUseCase getUseCase) {
        this.registerUseCase = registerUseCase;
        this.getUseCase = getUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<RestaurantDTO> registerRestaurant(@RequestBody RestaurantDTO dto) {
        var restaurante = registerUseCase.execute(dto.toEntity());
        return ResponseEntity.ok(RestaurantDTO.fromEntity(restaurante));
    }

    @GetMapping("/get")
    public ResponseEntity<List<RestaurantDTO>> getRestaurant(@RequestParam(required = false) String name, @RequestParam(required = false) String location, @RequestParam(required = false) String typeOfKitchen) {
        var restaurants = getUseCase.execute(name, location, typeOfKitchen);
        return ResponseEntity.ok(restaurants.stream().map(RestaurantDTO::fromEntity).toList());
    }
}