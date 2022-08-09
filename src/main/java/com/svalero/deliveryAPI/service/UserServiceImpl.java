package com.svalero.deliveryAPI.service;


import com.svalero.deliveryAPI.domain.Restaurant;
import com.svalero.deliveryAPI.domain.User;
import com.svalero.deliveryAPI.exception.RestaurantNotFoundException;
import com.svalero.deliveryAPI.exception.RiderNotFoundException;
import com.svalero.deliveryAPI.exception.UserNotFoundException;
import com.svalero.deliveryAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRespository;
    @Override
    public Flux<User> findAll() {
        return userRespository.findAll();
    }

    @Override
    public Mono<User> findById(long id)throws UserNotFoundException{
        return userRespository.findById(id)
                .onErrorReturn(new User());
    }

    @Override
    public Flux<User> findBySurname(String surname) {
        return userRespository.findBySurname(surname);
    }

    @Override
    public Mono<User> deleteUser(long id)throws UserNotFoundException {
        Mono<User> user = userRespository.findById(id)
                .onErrorReturn(new User());

        userRespository.delete(user.block());
        return user;
    }

    @Override
    public Mono<User> addUser(User user) {
        return userRespository.save(user);
    }

    @Override
    public Mono<User> modifyUser(long id, User newUser)throws UserNotFoundException {
        Mono<User> monoUser = userRespository.findById(id)
                .onErrorReturn(new User());
        User user = monoUser.block();

        user.setAddress(newUser.getAddress());
        user.setName(newUser.getName());
        user.setBirthDate(newUser.getBirthDate());
        user.setDni(newUser.getDni());
        user.setSurname(newUser.getSurname());
        return userRespository.save(user);
    }

    @Override
    public Mono<User> patchUser(long id, String address)throws UserNotFoundException {
        Mono<User> monoUser = userRespository.findById(id)
                .onErrorReturn(new User());
        User user = monoUser.block();
        user.setAddress(address);
        return userRespository.save(user);
    }
}
