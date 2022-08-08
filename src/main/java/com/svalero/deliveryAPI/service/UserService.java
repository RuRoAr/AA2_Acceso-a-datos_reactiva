package com.svalero.deliveryAPI.service;

import com.svalero.deliveryAPI.domain.User;
import com.svalero.deliveryAPI.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(long id) throws UserNotFoundException;
    List<User> findBySurname(String surname);

    User deleteUser(long id) throws UserNotFoundException;

    User addUser(User user);

    User modifyUser(long id, User newUser) throws UserNotFoundException;

    User patchUser(long id, String address) throws UserNotFoundException;
}
