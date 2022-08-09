package com.svalero.deliveryAPI.service;

import com.svalero.deliveryAPI.domain.Restaurant;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface RestaurantService {

    Flux<Restaurant> findAllRestaurants();
    Flux<Restaurant> findByCategory(String category);
    Mono<Restaurant> findRestaurant(long id) throws RestaurantNotFoundException;
    Mono<Restaurant> deleteRestaurant(long id) throws RestaurantNotFoundException;
    Mono<Restaurant> addRestaurant(Restaurant restaurant);
    Mono<Restaurant> modifyRestaurant(long id, Restaurant restaurant) throws RestaurantNotFoundException;

    Mono<Restaurant> patchRestaurant(long id, boolean operative) throws RestaurantNotFoundException;
}
