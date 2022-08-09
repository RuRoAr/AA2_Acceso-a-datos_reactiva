package com.svalero.deliveryAPI.domain.dto;

import com.svalero.deliveryAPI.domain.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class RiderDto {


    private double price;
    private double weight;
    private boolean ready;
    private int time;
    private int distance;
    private List<Order> order;


}
