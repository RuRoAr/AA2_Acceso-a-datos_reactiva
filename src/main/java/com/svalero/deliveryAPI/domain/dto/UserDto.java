package com.svalero.deliveryAPI.domain.dto;

import com.svalero.deliveryAPI.domain.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {


    private String dni;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String address;
    private List<Order> order;

}
