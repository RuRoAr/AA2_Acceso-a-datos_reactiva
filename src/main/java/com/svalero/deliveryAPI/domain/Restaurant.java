package com.svalero.deliveryAPI.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "restaurants")
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    @NotNull
    @NotEmpty
    private String name;
    @Column
    @NotNull
    @NotEmpty
    private String address;
    @Column
    @PositiveOrZero
    private int capacity;
    @Column
    private  boolean operative;
    @Column(name = "medium_price")
    @PositiveOrZero
    private float mediumPrice;
    @Column
    @NotEmpty
    private String category;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

}
