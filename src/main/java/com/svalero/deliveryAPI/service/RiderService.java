package com.svalero.deliveryAPI.service;

import com.svalero.deliveryAPI.domain.Rider;
import com.svalero.deliveryAPI.exception.RiderNotFoundException;

import java.util.List;

public interface RiderService {

    List<Rider> findAll();
    Rider findById(long id) throws RiderNotFoundException;
    Rider findRider(long id) throws RiderNotFoundException;

    List<Rider> findByVehicle(String vehicle);

    Rider deleteRider(long id) throws RiderNotFoundException;

    Rider addRider(Rider rider);

    Rider modifyRider(long id, Rider NewRider) throws RiderNotFoundException;

    Rider patchRider(long id, String name)throws RiderNotFoundException;
}
