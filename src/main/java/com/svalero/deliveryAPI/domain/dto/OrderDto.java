package com.svalero.deliveryAPI.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor

public class OrderDto {


    private double price;
    private double weight;
    private boolean ready;
    private int time;
    private int distance;
    private long restaurant;// no poner restaurantId, se lia
    private long user;
    private long rider;

}
