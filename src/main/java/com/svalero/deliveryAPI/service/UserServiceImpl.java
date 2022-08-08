package com.svalero.deliveryAPI.service;


import com.svalero.deliveryAPI.domain.Restaurant;
import com.svalero.deliveryAPI.domain.User;
import com.svalero.deliveryAPI.exception.RestaurantNotFoundException;
import com.svalero.deliveryAPI.exception.RiderNotFoundException;
import com.svalero.deliveryAPI.exception.UserNotFoundException;
import com.svalero.deliveryAPI.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRespository userRespository;
    @Override
    public List<User> findAll() {
        return userRespository.findAll();
    }

    @Override
    public User findById(long id)throws UserNotFoundException{
        return userRespository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> findBySurname(String surname) {
        return userRespository.findBySurname(surname);
    }

    @Override
    public User deleteUser(long id)throws UserNotFoundException {
        User user = userRespository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        userRespository.delete(user);
        return user;
    }

    @Override
    public User addUser(User user) {
        return userRespository.save(user);
    }

    @Override
    public User modifyUser(long id, User newUser)throws UserNotFoundException {
        User user = userRespository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        user.setAddress(newUser.getAddress());
        user.setName(newUser.getName());
        user.setBirthDate(newUser.getBirthDate());
        user.setDni(newUser.getDni());
        user.setSurname(newUser.getSurname());
        return userRespository.save(user);
    }

    @Override
    public User patchUser(long id, String address)throws UserNotFoundException {
        User user = userRespository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        user.setAddress(address);
        return userRespository.save(user);
    }
}
