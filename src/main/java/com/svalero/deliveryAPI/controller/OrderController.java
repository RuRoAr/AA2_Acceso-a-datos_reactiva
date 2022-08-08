package com.svalero.deliveryAPI.controller;


import com.svalero.deliveryAPI.domain.Order;
import com.svalero.deliveryAPI.domain.Restaurant;
import com.svalero.deliveryAPI.domain.Rider;
import com.svalero.deliveryAPI.domain.dto.OrderDto;
import com.svalero.deliveryAPI.exception.*;
import com.svalero.deliveryAPI.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        logger.info("Get Orders" );
        List<Order> orders = orderService.findAll();
        logger.info("End Get Orders" );
        return ResponseEntity.ok(orders);
    }


    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable long id) throws OrderNotFoundException{
        logger.info("Get Order for id: " + id );
        Order order= orderService.findOrder(id);
        logger.info("End Get Order for id: " + id );
        return ResponseEntity.ok(order);
    }
    @GetMapping("/order")
    public ResponseEntity<List<Order>> getOrderByDistance(@RequestParam(name = "distance", defaultValue = "")int distance) {//?=
        List<Order> orders;
        logger.info("Get Order for time: " + distance );
        if (distance != 0) {
            orders = orderService.findByDistance(distance);
        } else {
            orders = orderService.findAll();
        }
        logger.info("End Get Order for time: " + distance );
        return ResponseEntity.ok(orders);
    }
    @DeleteMapping("/order/{id}")
    public ResponseEntity<Order> removeOrder(@PathVariable long id) throws OrderNotFoundException {
        logger.info("Delete Order: " + id );
        Order order = orderService.deleteOrder(id);
        logger.info("End Delete Order: " + id );
        return ResponseEntity.ok(order);
    }
//    @PostMapping("/orders")
//    public Order addOrder(@RequestBody Order order) {//lo combierte a json
//        Order newOrder = orderService.addOrder(order);
//        return newOrder;
//    }
    @PostMapping("/orders")
    public ResponseEntity<Order> addOrder(@RequestBody OrderDto orderDto)throws UserNotFoundException,
            RestaurantNotFoundException, RiderNotFoundException {//lo combierte a json
//        Order newOrder = orderService.addOrder(orderDto);
//        return newOrder;
        logger.info("Add Order: " );
        return ResponseEntity.ok(orderService.addOrder(orderDto));
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Order> modifyOrder(@RequestBody Order order, @PathVariable long id)throws OrderNotFoundException {
        logger.info("Modify Order: " + id );
        Order newOrder = orderService.modifyOrder(id, order);
        logger.info("End Modify Order: " + id );
        return ResponseEntity.ok(newOrder);
    }
    @PatchMapping("/order/{id}")//cambiar el estado de un pedido
    public ResponseEntity<Order> patchOrder(@PathVariable long id, @RequestBody boolean ready) throws OrderNotFoundException {
        logger.info("Start PatchOrder " + id);
        Order order = orderService.patchOrder(id, ready);
        logger.info("End patchRider " + id);
        return ResponseEntity.ok(order);
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBikeNotFoundException(OrderNotFoundException bnfe) {
        ErrorResponse errorResponse = ErrorResponse.generalError(101, bnfe.getMessage());
        logger.error(bnfe.getMessage(), bnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = ErrorResponse.generalError(999, "Internal server error");
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException manve) {
        Map<String, String> errors = new HashMap<>();
        manve.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return ResponseEntity.badRequest().body(ErrorResponse.validationError(errors));
    }
}
