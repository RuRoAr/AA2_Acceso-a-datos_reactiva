package com.svalero.deliveryAPI.service;

import com.svalero.deliveryAPI.domain.Restaurant;
import com.svalero.deliveryAPI.exception.RestaurantNotFoundException;
import lombok.Data;

import java.util.List;


public interface RestaurantService {

List<Restaurant> findAllRestaurants();
List<Restaurant> findByCategory(String category);
Restaurant findRestaurant(long id) throws RestaurantNotFoundException;
Restaurant deleteRestaurant(long id) throws RestaurantNotFoundException;
Restaurant addRestaurant(Restaurant restaurant);
Restaurant modifyRestaurant(long id, Restaurant restaurant) throws RestaurantNotFoundException;

    Restaurant patchRestaurant(long id, boolean operative) throws RestaurantNotFoundException;
}
