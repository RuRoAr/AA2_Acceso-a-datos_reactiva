package com.svalero.deliveryAPI.controller;


import com.svalero.deliveryAPI.domain.Order;
import com.svalero.deliveryAPI.domain.Restaurant;
import com.svalero.deliveryAPI.domain.Rider;
import com.svalero.deliveryAPI.domain.User;
import com.svalero.deliveryAPI.exception.ErrorResponse;
import com.svalero.deliveryAPI.exception.RiderNotFoundException;
import com.svalero.deliveryAPI.exception.UserNotFoundException;
import com.svalero.deliveryAPI.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Flux<User>> getAllUsers() {
        logger.info("Find Users " );
        Flux<User> users = userService.findAll();
        logger.info("End Find Users " );
        return ResponseEntity.ok(users);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<Mono<User>> getUser(@PathVariable long id)throws UserNotFoundException{
        logger.info("Find Users by id:" + id );
        Mono<User> user= userService.findById(id);
        logger.info("End Find Users by id:" + id );
        return ResponseEntity.ok(user);
    }
    @GetMapping("/user")
    public ResponseEntity<Flux<User>> getUserBySurname(@RequestParam(name = "Surname", defaultValue = "")
                                                            String surname) {//?=
        logger.info("Find Users by surname:" + surname );
        Flux<User> users;
        if (surname.equals("")) {
            users= userService.findAll();
        } else {
            users = userService.findBySurname(surname);
        }
        logger.info("End Find Users by surname:" + surname );
        return ResponseEntity.ok(users);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Mono<User>> removeUser(@PathVariable long id)throws UserNotFoundException {
        logger.info("Delete Users by id:" + id );
        Mono<User> user = userService.deleteUser(id);
        logger.info("End Delete Users by id:" + id );
        return ResponseEntity.ok(user);
    }
    @PostMapping("/users")
    public ResponseEntity<Mono<User>> addUser(@RequestBody User user) {//lo combierte a json
        logger.info("Add Users by id:" );
        Mono<User> newUser = userService.addUser(user);
        logger.info("End Add Users by id:" );
        return ResponseEntity.ok(newUser);
    }
    @PutMapping("/user/{id}")
    public ResponseEntity<Mono<User>> modifyUser(@RequestBody User user, @PathVariable long id)throws UserNotFoundException {
        logger.info("Modify Users by id:" + id );
        Mono<User> newUser = userService.modifyUser(id, user);
        logger.info("End Modify Users by id:" + id );
        return ResponseEntity.ok(newUser);
    }
    @PatchMapping("/user/{id}")
    public ResponseEntity<Mono<User>> patchUser(@PathVariable long id, @RequestBody String address) throws UserNotFoundException {
        logger.info("Start PatchUser " + id);
        Mono<User> user = userService.patchUser(id, address);
        logger.info("End patchUser " + id);
        return ResponseEntity.ok(user);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBikeNotFoundException(UserNotFoundException bnfe) {
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
