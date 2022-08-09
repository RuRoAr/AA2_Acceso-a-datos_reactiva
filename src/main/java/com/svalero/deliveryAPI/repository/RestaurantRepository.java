package com.svalero.deliveryAPI.repository;

import com.svalero.deliveryAPI.domain.Restaurant;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface RestaurantRepository extends ReactiveMongoRepository<Restaurant, Long> {

Flux<Restaurant> findAll();
Flux<Restaurant> findByCategory(String category);

}
