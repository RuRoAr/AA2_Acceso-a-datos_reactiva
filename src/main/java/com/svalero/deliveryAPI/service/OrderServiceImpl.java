package com.svalero.deliveryAPI.service;

import com.svalero.deliveryAPI.domain.Order;
import com.svalero.deliveryAPI.domain.Restaurant;
import com.svalero.deliveryAPI.domain.Rider;
import com.svalero.deliveryAPI.domain.User;
import com.svalero.deliveryAPI.domain.dto.OrderDto;
import com.svalero.deliveryAPI.repository.OrderRepository;
import com.svalero.deliveryAPI.repository.RestaurantRepository;
import com.svalero.deliveryAPI.repository.RiderRepository;
import com.svalero.deliveryAPI.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RiderRepository riderRepository;


    @Override
    public Flux<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Mono<Order> findOrder(long id)throws OrderNotFoundException {
        return orderRepository.findById(id) //si no esta el objeto mandame esta excepcion
                .onErrorReturn(new Order());
    }

    @Override
    public Flux<Order> findByDistance(int distance) {
        return orderRepository.findByDistance(distance);
    }

    @Override
    public Mono<Order> deleteOrder(long id)throws OrderNotFoundException {
        Mono<Order> order = orderRepository.findById(id)
                .onErrorReturn(new Order());

        orderRepository.delete(order.block());
        return order;
    }

    @Override
    public Mono<Order> addOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Mono<Order> addOrder(OrderDto orderDto) throws UserNotFoundException, RestaurantNotFoundException, RiderNotFoundException {
        Mono<User> monUser = userRepository.findById(orderDto.getUser())
                .onErrorReturn(new User());
        Mono<Restaurant> monoRestaurant = restaurantRepository.findById(orderDto.getRestaurant())
                .onErrorReturn(new Restaurant());
        Mono<Rider> monoRider = riderRepository.findById(orderDto.getRider())
                .onErrorReturn(new Rider());

//        Order order = new Order();
//        ModelMapper mapper = new ModelMapper();//mapea los objetos, es decir, comounia los objetos con los mismos atributos
//
//        mapper.map(orderDto, order);

        Restaurant restaurant = monoRestaurant.block();
        User user = monUser.block();
        Rider rider = monoRider.block();

        ModelMapper mapper = new ModelMapper();
        Order order = mapper.map(orderDto, Order.class);

        order.setRestaurant(restaurant);
        order.setRider(rider);
        order.setUser(user);
        return orderRepository.save(order);

    }

    @Override
    public Mono<Order> modifyOrder(long id, Order newOrder)throws OrderNotFoundException {
        Mono<Order> monoOrder = orderRepository.findById(id).onErrorReturn(new Order());
        Order order = monoOrder.block();
        order.setDistance(newOrder.getDistance());
        order.setPrice(newOrder.getPrice());
        order.setReady(newOrder.isReady());
        order.setTime(newOrder.getTime());
        order.setWeight(newOrder.getWeight());
        return orderRepository.save(order);
    }

    @Override
    public Flux<Order> findOrders(Rider rider, int distance) {
        return orderRepository.findByRiderAndDistance(rider,distance);
    }

    @Override
    public Flux<Order> findOrders(Rider rider) {
        return orderRepository.findByRider(rider);
    }

    @Override
    public Mono<Order> patchOrder(long id, boolean ready)throws OrderNotFoundException {
       Mono<Order> monoOrder = orderRepository.findById(id)
                .onErrorReturn(new Order());

        Order order = monoOrder.block();
        order.setReady(ready);
        return orderRepository.save(order);
    }

//    @Override
//    public  int numOrders(long idRider)throws  RiderNotFoundException {
//        Mono<Order> order = orderRepository.findById(idRider)
//                .onErrorReturn(new Order());
//        return orderRepository.numOrder(idRider);
//    }


}
