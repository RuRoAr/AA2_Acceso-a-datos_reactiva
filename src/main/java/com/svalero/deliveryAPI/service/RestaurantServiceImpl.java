package com.svalero.deliveryAPI.service;


import com.svalero.deliveryAPI.domain.Order;
import com.svalero.deliveryAPI.domain.Restaurant;
import com.svalero.deliveryAPI.exception.OrderNotFoundException;
import com.svalero.deliveryAPI.exception.RestaurantNotFoundException;
import com.svalero.deliveryAPI.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    @Autowired
    private RestaurantRepository restaurantRepository;


    @Override
    public List<Restaurant> findAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> findByCategory(String category) {
        return restaurantRepository.findByCategory(category);
    }

    @Override
    public Restaurant findRestaurant(long id)throws RestaurantNotFoundException {
        return restaurantRepository.findById(id)
                .orElseThrow(()-> new RestaurantNotFoundException());
    }

    @Override
    public Restaurant deleteRestaurant(long id)throws RestaurantNotFoundException {
        Restaurant restaurant= restaurantRepository.findById(id)
                .orElseThrow(()-> new RestaurantNotFoundException());;
        restaurantRepository.delete(restaurant);
        return restaurant;
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {


        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant modifyRestaurant(long id, Restaurant newRestaurant)throws RestaurantNotFoundException {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(()-> new RestaurantNotFoundException());
        restaurant.setAddress(newRestaurant.getAddress());
        restaurant.setCapacity(newRestaurant.getCapacity());
        restaurant.setCategory(newRestaurant.getCategory());
        restaurant.setName(newRestaurant.getName());
        restaurant.setOperative(newRestaurant.isOperative());
        restaurant.setMediumPrice(newRestaurant.getMediumPrice());
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant patchRestaurant(long id, boolean operative)throws RestaurantNotFoundException {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(RestaurantNotFoundException::new);
        restaurant.setOperative(operative);
        return restaurantRepository.save(restaurant);
    }
}
