package com.svalero.deliveryAPI.service;


import com.svalero.deliveryAPI.domain.Rider;
import com.svalero.deliveryAPI.exception.OrderNotFoundException;
import com.svalero.deliveryAPI.exception.RiderNotFoundException;
import com.svalero.deliveryAPI.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RiderServiceImpl implements RiderService{
    @Autowired
    private RiderRepository riderRepository;

    @Override
    public Flux<Rider> findAll() {
        return riderRepository.findAll();
    }

    @Override
    public Mono<Rider> findById(long id)throws RiderNotFoundException {
        return riderRepository.findById(id)
                .onErrorReturn(new Rider());
    }

    @Override
    public Mono<Rider> findRider(long id) throws RiderNotFoundException {
        return riderRepository.findById(id)
                .onErrorReturn(new Rider());
    }

    @Override
    public Flux<Rider> findByVehicle(String vehicle) {
        return riderRepository.findByVehicle(vehicle);
    }

    @Override
    public Mono<Rider> deleteRider(long id)throws RiderNotFoundException {
       Mono<Rider> rider = riderRepository.findById(id)
                       .onErrorReturn(new Rider());

       riderRepository.delete(rider.block());
       return rider;
    }

    @Override
    public Mono<Rider> addRider(Rider rider) {
        return riderRepository.save(rider);

    }

    @Override
    public Mono<Rider> modifyRider(long id, Rider newRider)throws RiderNotFoundException {
        Mono<Rider> monoRider = riderRepository.findById(id)
                .onErrorReturn(new Rider());
        Rider rider = monoRider.block();
        rider.setName(newRider.getName());
        rider.setDni(newRider.getDni());
        rider.setSurname(newRider.getSurname());
        rider.setMaxSpeed(newRider.getMaxSpeed());
        rider.setVehicle(newRider.getVehicle());
        return riderRepository.save(rider);
    }

    @Override
    public Mono<Rider> patchRider(long id, String name) throws RiderNotFoundException {
        Mono<Rider> monoRider = riderRepository.findById(id)
                .onErrorReturn(new Rider());
        Rider rider = monoRider.block();
        rider.setName(name);
        return riderRepository.save(rider);
    }
}
