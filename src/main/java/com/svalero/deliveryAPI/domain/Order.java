package com.svalero.deliveryAPI.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.lang.annotation.Documented;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "orders")
public class Order {
    @Id
    private String id;
    @Field
    @NotNull
    @PositiveOrZero
    private long price;
    @Field
    @PositiveOrZero
    private double weight;
    @Field
    private boolean ready;
    @Field
    @Min(1)
    private int time;
    @Field
    @PositiveOrZero
    private int distance;

    @ManyToOne
    @JoinColumn(name = "rider_id")
    @JsonBackReference(value = "rider-order")//esto tambien hace que no
    // haga un bucle y por eso no devuleve en postman
    private Rider rider;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    @JsonBackReference(value = "restaurant-order")
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-order")
    private User user;
}
