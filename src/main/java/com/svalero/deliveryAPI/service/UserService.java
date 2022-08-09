package com.svalero.deliveryAPI.service;

import com.svalero.deliveryAPI.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Flux<User> findAll();
    Mono<User> findById(long id) throws UserNotFoundException;
    Flux<User> findBySurname(String surname);

    Mono<User> deleteUser(long id) throws UserNotFoundException;

    Mono<User> addUser(User user);

    Mono<User> modifyUser(long id, User newUser) throws UserNotFoundException;

    Mono<User> patchUser(long id, String address) throws UserNotFoundException;
}
