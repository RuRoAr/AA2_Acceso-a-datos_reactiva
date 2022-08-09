package com.svalero.deliveryAPI.controller;


import com.svalero.deliveryAPI.domain.Restaurant;
import com.svalero.deliveryAPI.exception.BadRequestException;
import com.svalero.deliveryAPI.exception.ErrorResponse;
import com.svalero.deliveryAPI.exception.InternalServerErrorException;
import com.svalero.deliveryAPI.exception.RestaurantNotFoundException;
import com.svalero.deliveryAPI.service.OrderService;
import com.svalero.deliveryAPI.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RestaurantController {

    private final Logger logger = LoggerFactory.getLogger(RestaurantController.class);
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private OrderService orderService;
    @GetMapping("/restaurants")
    public ResponseEntity<Flux<Restaurant>> getAllRestaurants(){
        logger.info("Start getRestaurant");
        Flux<Restaurant> restaurants = restaurantService.findAllRestaurants();
        logger.info("End getRestaurant");
        return ResponseEntity.ok(restaurants);
    }
    @GetMapping("/restaurant/{id}")
    public ResponseEntity <Mono<Restaurant>> getRestaurant(@PathVariable long id)throws RestaurantNotFoundException{
        logger.info("Start getRestaurant: " + id);
        Mono<Restaurant> restaurant= restaurantService.findRestaurant(id);
        logger.info("End getRestaurant: " + id);
        return ResponseEntity.ok(restaurant);
    }
    @GetMapping("/restaurant")
    public ResponseEntity<Flux<Restaurant>> getRestaurantByCategory( @RequestParam(name = "category", defaultValue = "0") String category) {//?=
        Flux<Restaurant> restaurants;
        logger.info("Found Restaurant: " + category );
        if (category.equals("0")) {
            restaurants = restaurantService.findAllRestaurants();
        } else {
            restaurants = restaurantService.findByCategory(category);
        }
        logger.info("End Found Restaurant: " + category );
        return ResponseEntity.ok(restaurants);
    }

    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<Mono<Restaurant>> removeRestaurant(@PathVariable long id)throws RestaurantNotFoundException {
        logger.info("Delete Restaurant: " + id );
        Mono<Restaurant> restaurant = restaurantService.deleteRestaurant(id);
        logger.info("End delete Restaurant: " + id );
        return ResponseEntity.ok(restaurant);
    }
    @PostMapping("/restaurants")
    public ResponseEntity<Mono<?>> addRestaurant(@RequestBody Restaurant restaurant) {//lo combierte a json
       // ResponseEntity
        logger.info("Add Restaurant "  );
        Mono<Restaurant> newRestaurant = restaurantService.addRestaurant(restaurant);
        logger.info("End Add Restaurant " );
        return ResponseEntity.ok(newRestaurant);
    }
    @PutMapping("/restaurant/{id}")
    public ResponseEntity<Mono<Restaurant>> modifyRestaurant(@RequestBody Restaurant restaurant, @PathVariable long id)throws  RestaurantNotFoundException {
        logger.info("Modify Restaurant: " + id );
        Mono<Restaurant> newRestaurant = restaurantService.modifyRestaurant(id, restaurant);
        logger.info("End Modify Restaurant: " + id );
        return ResponseEntity.ok(newRestaurant);
    }

    @PatchMapping("/restaurant/{id}")
    public ResponseEntity<Mono<Restaurant>> patchRestaurant(@PathVariable long id, @RequestBody boolean operative) throws RestaurantNotFoundException {
        logger.info("Start PatchRestaurant " + id);
        Mono<Restaurant> restaurant = restaurantService.patchRestaurant(id, operative);
        logger.info("End patchRider " + id);
        return ResponseEntity.ok(restaurant);
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException manve) {
        logger.info("400: Argument not valid");
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return ResponseEntity.badRequest().body(ErrorResponse.validationError(errors));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException bre) {
        logger.info("400: Bad request");
        return ResponseEntity.badRequest().body(ErrorResponse.badRequest(bre.getMessage()));
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCountryNotFoundException(RestaurantNotFoundException cnfe) {
        logger.info("404: Country not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.resourceNotFound(cnfe.getMessage()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(InternalServerErrorException isee) {
        logger.info("500: Internal server error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.internalServerError(isee.getMessage()));
    }
    }
