package com.svalero.deliveryAPI.service;


import com.svalero.deliveryAPI.domain.Order;
import com.svalero.deliveryAPI.domain.Restaurant;
import com.svalero.deliveryAPI.exception.OrderNotFoundException;
import com.svalero.deliveryAPI.exception.RestaurantNotFoundException;
import com.svalero.deliveryAPI.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;


    @Override
    public Flux<Restaurant> findAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Flux<Restaurant> findByCategory(String category) {
        return restaurantRepository.findByCategory(category);
    }

    @Override
    public Mono<Restaurant> findRestaurant(long id)throws RestaurantNotFoundException {
        return restaurantRepository.findById(id)
                .onErrorReturn(new Restaurant());
    }

    @Override
    public Mono<Restaurant> deleteRestaurant(long id)throws RestaurantNotFoundException {
        Mono<Restaurant> restaurant= restaurantRepository.findById(id)
                        .onErrorReturn(new Restaurant());
        restaurantRepository.delete(restaurant.block());
        return restaurant;
    }

    @Override
    public Mono<Restaurant> addRestaurant(Restaurant restaurant) {


        return restaurantRepository.save(restaurant);
    }

    @Override
    public Mono<Restaurant> modifyRestaurant(long id, Restaurant newRestaurant)throws RestaurantNotFoundException {
        Mono<Restaurant> monoRestaurant = restaurantRepository.findById(id)
                        .onErrorReturn(new Restaurant());
        Restaurant restaurant = monoRestaurant.block();
        restaurant.setAddress(newRestaurant.getAddress());
        restaurant.setCapacity(newRestaurant.getCapacity());
        restaurant.setCategory(newRestaurant.getCategory());
        restaurant.setName(newRestaurant.getName());
        restaurant.setOperative(newRestaurant.isOperative());
        restaurant.setMediumPrice(newRestaurant.getMediumPrice());
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Mono<Restaurant> patchRestaurant(long id, boolean operative)throws RestaurantNotFoundException {
        Mono<Restaurant> monoRestaurant = restaurantRepository.findById(id)
                .onErrorReturn(new Restaurant());
        Restaurant restaurant = monoRestaurant.block();
        restaurant.setOperative(operative);
        return restaurantRepository.save(restaurant);
    }
}
