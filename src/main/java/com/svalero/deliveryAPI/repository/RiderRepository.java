package com.svalero.deliveryAPI.repository;

import com.svalero.deliveryAPI.domain.Restaurant;
import com.svalero.deliveryAPI.domain.Rider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RiderRepository extends CrudRepository<Rider,Long> {

    List<Rider> findAll();
    List<Rider> findByVehicle(String vehicle);

    }