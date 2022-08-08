package com.svalero.deliveryAPI.repository;

import com.svalero.deliveryAPI.domain.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

List<Restaurant>findAll();
List<Restaurant> findByCategory(String category);

}
