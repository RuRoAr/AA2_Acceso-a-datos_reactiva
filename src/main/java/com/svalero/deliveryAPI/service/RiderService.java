package com.svalero.deliveryAPI.service;

import com.svalero.deliveryAPI.domain.Restaurant;
import com.svalero.deliveryAPI.domain.Rider;
import com.svalero.deliveryAPI.exception.RiderNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RiderService {

    Flux<Rider> findAll();
    Mono<Rider> findById(long id) throws RiderNotFoundException;
    Mono<Rider> findRider(long id) throws RiderNotFoundException;

    Flux<Rider> findByVehicle(String vehicle);

    Mono<Rider> deleteRider(long id) throws RiderNotFoundException;

    Mono<Rider> addRider(Rider rider);

    Mono<Rider> modifyRider(long id, Rider NewRider) throws RiderNotFoundException;

    Mono<Rider> patchRider(long id, String name)throws RiderNotFoundException;
}
