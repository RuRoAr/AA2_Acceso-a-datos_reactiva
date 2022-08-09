package com.svalero.deliveryAPI.repository;

import com.svalero.deliveryAPI.domain.Order;
import com.svalero.deliveryAPI.domain.Rider;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<Order, Long> {


    Flux<Order> findByRider(Rider rider);
    Flux<Order> findByRiderAndDistance(Rider rider, int distance);
    Flux<Order> findAll();
    Flux<Order> findByDistance(int distance);

//    @Query(value = "SELECT COUNT(*) FROM \"orders\" WHERE \"rider_id\" = ?97", nativeQuery = true)
//    int numOrder(long idRider);
}
