package com.svalero.deliveryAPI.domain.dto;

import com.svalero.deliveryAPI.domain.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class RestaurantDto {

    private String name;
    private String address;
    private int capacity;
    private boolean operative;
    private float mediumPrice;
    private String category;
    private List<Order> order;

}
