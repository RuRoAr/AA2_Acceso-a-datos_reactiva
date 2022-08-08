package com.svalero.deliveryAPI.repository;

import com.svalero.deliveryAPI.domain.Order;
import com.svalero.deliveryAPI.domain.Rider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {


    List<Order> findByRider(Rider rider);
    List<Order> findByRiderAndDistance(Rider rider, int distance);
    List<Order> findAll();
    List<Order> findByDistance(int distance);

    @Query(value = "SELECT COUNT(*) FROM \"orders\" WHERE \"rider_id\" = ?97", nativeQuery = true)
    int numOrder(long idRider);
}
