package com.svalero.deliveryAPI.service;


import com.svalero.deliveryAPI.domain.Rider;
import com.svalero.deliveryAPI.exception.OrderNotFoundException;
import com.svalero.deliveryAPI.exception.RiderNotFoundException;
import com.svalero.deliveryAPI.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiderServiceImpl implements RiderService{
    @Autowired
    private RiderRepository riderRepository;

    @Override
    public List<Rider> findAll() {
        return riderRepository.findAll();
    }

    @Override
    public Rider findById(long id)throws RiderNotFoundException {
        return riderRepository.findById(id)
                .orElseThrow(RiderNotFoundException::new);
    }

    @Override
    public Rider findRider(long id) throws RiderNotFoundException {
        return riderRepository.findById(id)
                .orElseThrow(RiderNotFoundException::new);
    }

    @Override
    public List<Rider> findByVehicle(String vehicle) {
        return riderRepository.findByVehicle(vehicle);
    }

    @Override
    public Rider deleteRider(long id)throws RiderNotFoundException {
       Rider rider = riderRepository.findById(id)
               .orElseThrow(RiderNotFoundException::new);;
       riderRepository.delete(rider);
       return rider;
    }

    @Override
    public Rider addRider(Rider rider) {
        return riderRepository.save(rider);

    }

    @Override
    public Rider modifyRider(long id, Rider newRider)throws RiderNotFoundException {
        Rider rider = riderRepository.findById(id)
                .orElseThrow(()-> new RiderNotFoundException());;
        rider.setName(newRider.getName());
        rider.setDni(newRider.getDni());
        rider.setSurname(newRider.getSurname());
        rider.setMaxSpeed(newRider.getMaxSpeed());
        rider.setVehicle(newRider.getVehicle());
        return riderRepository.save(rider);
    }

    @Override
    public Rider patchRider(long id, String name) throws RiderNotFoundException {
        Rider rider = riderRepository.findById(id)
                .orElseThrow(RiderNotFoundException::new);
        rider.setName(name);
        return riderRepository.save(rider);
    }
}
