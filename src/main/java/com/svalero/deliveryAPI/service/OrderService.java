package com.svalero.deliveryAPI.service;

import com.svalero.deliveryAPI.domain.Order;
import com.svalero.deliveryAPI.domain.Rider;
import com.svalero.deliveryAPI.domain.dto.OrderDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {

    Flux<Order> findAll();
    Mono<Order> findOrder(long id) throws OrderNotFoundException;

    Flux<Order> findByDistance(int distance);
    Mono<Order> deleteOrder(long id) throws OrderNotFoundException;


    Mono<Order> addOrder(Order order);

    Mono<Order> addOrder(OrderDto orderDto) throws UserNotFoundException, RestaurantNotFoundException, RiderNotFoundException;

    Mono<Order> modifyOrder(long id, Order newOrder) throws OrderNotFoundException;

    Flux<Order> findOrders(Rider rider, int distance);

    Flux<Order> findOrders(Rider rider);

    Mono<Order> patchOrder(long id, boolean ready) throws OrderNotFoundException;

    // int numOrders(long idRider) throws OrderNotFoundException, RiderNotFoundException;
}
