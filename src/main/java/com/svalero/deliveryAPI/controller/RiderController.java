package com.svalero.deliveryAPI.controller;

import com.svalero.deliveryAPI.domain.Order;
import com.svalero.deliveryAPI.domain.Rider;
import com.svalero.deliveryAPI.exception.ErrorResponse;
import com.svalero.deliveryAPI.exception.OrderNotFoundException;
import com.svalero.deliveryAPI.exception.RiderNotFoundException;
import com.svalero.deliveryAPI.service.OrderService;
import com.svalero.deliveryAPI.service.RiderService;
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
public class RiderController {
    private final Logger logger = LoggerFactory.getLogger(RiderController.class);
    @Autowired
    private RiderService riderService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/riders")
    public ResponseEntity<List<Rider>> getAllRiders() {
        logger.info("Find Riders " );
        List<Rider> riders = riderService.findAll();
        logger.info("End Find Riders " );
        return ResponseEntity.ok(riders);
    }
    @GetMapping("/rider/{id}")
    public ResponseEntity<Rider> getRider(@PathVariable long id)throws RiderNotFoundException{
        logger.info("Find rider by id: " + id );
        Rider rider= riderService.findById(id);
        logger.info("End  rider by id: " + id );
        return ResponseEntity.ok(rider);
    }
    @GetMapping("/rider")
    public ResponseEntity<List<Rider>> getRiderByVehicle(@RequestParam(name = "vehicle", defaultValue = "")
                                                            String vehicle) {//?=
        List<Rider> riders;
        logger.info("Find rider by velocidad maxima: " + vehicle );
        if (vehicle.equals("")) {
            riders = riderService.findAll();
        } else {
            riders = riderService.findByVehicle(vehicle);
        }
        logger.info("End Find rider by vehicle: " + vehicle );
        return ResponseEntity.ok(riders);
    }
    @DeleteMapping("/rider/{id}")
    public ResponseEntity<Rider> removeRider(@PathVariable long id)throws RiderNotFoundException {
        logger.info("Delete rider id: " + id);
        Rider rider = riderService.deleteRider(id);
        logger.info("End Delete rider id:" + id );
        return ResponseEntity.ok(rider);
    }
    @PostMapping("/riders")
    public ResponseEntity<Rider> addRider(@RequestBody Rider rider) {//lo combierte a json
        logger.info("Add rider " );
        Rider newRider = riderService.addRider(rider);
        logger.info("End Add rider " );
        return ResponseEntity.ok(newRider);
    }
    @PutMapping("/rider/{id}")
    public ResponseEntity<Rider> modifyRider(@RequestBody Rider rider, @PathVariable long id)throws RiderNotFoundException {
        logger.info("Modify rider id:" + id );
        Rider newRider = riderService.modifyRider(id, rider);
        logger.info("End Modify rider id:" + id );
        return ResponseEntity.ok(newRider);
    }
    @GetMapping("/rider/{riderId}/orders")//ordenes de un rider
    public ResponseEntity<List<Order>> getOrders(@PathVariable long riderId,
                                 @RequestParam(name = "distance", defaultValue = "0")
                                         int distance) throws RiderNotFoundException {
        List<Order> orderList = null;
        logger.info("Find rider by id: " + riderId );
        Rider rider = riderService.findRider(riderId);

        if (distance != 0) {
            logger.info("Find rider by id2: " + riderId );
            orderList = orderService.findOrders(rider, distance);
        } else {
            logger.info("Find rider by id3: " + riderId );
            orderList = orderService.findOrders(rider);
            logger.info("Find rider by id4: " + riderId );
        }
        logger.info("End Find rider by id: " + riderId );
        return ResponseEntity.ok(orderList);
    }
    @PatchMapping("/rider/{id}")
    public ResponseEntity<Rider> patchRider(@PathVariable long id, @RequestBody String name) throws RiderNotFoundException {
        logger.info("Start PatchRider " + id);
        Rider rider = riderService.patchRider(id, name);
        logger.info("End patchRider " + id);
        return ResponseEntity.ok(rider);
    }
    // Contar las orders de un usuario
    @GetMapping("/user/{id}/numOrders")
    public ResponseEntity<Integer> numOrdersRider(@PathVariable long idRider) throws OrderNotFoundException, RiderNotFoundException, OrderNotFoundException {
        logger.info("Start numOrdersRider " + idRider);
        int users = orderService.numOrders(idRider);
        logger.info("End numOrdersRider " + idRider);
        return ResponseEntity.ok(users);
    }
    @ExceptionHandler(RiderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBikeNotFoundException(RiderNotFoundException bnfe) {
        ErrorResponse errorResponse = ErrorResponse.generalError(101, bnfe.getMessage());
        logger.error(bnfe.getMessage(), bnfe);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // TODO Más tipos de excepciones que puedan generar errores

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
