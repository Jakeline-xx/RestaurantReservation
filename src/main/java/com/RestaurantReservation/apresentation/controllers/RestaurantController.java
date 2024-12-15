package com.RestaurantReservation.apresentation.controllers;

import com.RestaurantReservation.application.dtos.RestaurantDTO;
import com.RestaurantReservation.application.usecases.RegisterRestaurantUseCase;
import com.RestaurantReservation.application.usecases.SearchRestaurantUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RegisterRestaurantUseCase registerUseCase;
    private final SearchRestaurantUseCase searchUseCase;

    public RestaurantController(RegisterRestaurantUseCase registerUseCase, SearchRestaurantUseCase searchUseCase) {
        this.registerUseCase = registerUseCase;
        this.searchUseCase = searchUseCase;
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> register(@RequestBody RestaurantDTO dto) {
        var restaurante = registerUseCase.execute(dto.toEntity());
        return ResponseEntity.ok(RestaurantDTO.fromEntity(restaurante));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> search(@RequestParam(required = false) String name, @RequestParam(required = false) String location, @RequestParam(required = false) String typeOfKitchen) {
        var restaurants = searchUseCase.execute(name, location, typeOfKitchen);
        return ResponseEntity.ok(restaurants.stream().map(RestaurantDTO::fromEntity).toList());
    }
}