package com.svalero.deliveryAPI.repository;

import com.svalero.deliveryAPI.domain.Restaurant;
import com.svalero.deliveryAPI.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRespository extends CrudRepository<User, Long> {
    List<User> findAll();
    List<User> findBySurname(String surname);
}
