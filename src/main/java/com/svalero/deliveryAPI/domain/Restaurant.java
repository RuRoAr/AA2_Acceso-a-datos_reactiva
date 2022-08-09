package com.svalero.deliveryAPI.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "restaurants")
public class Restaurant {
    @Id
    private String id;
    @Field
    @NotNull
    @NotEmpty
    private String name;
    @Field
    @NotNull
    @NotEmpty
    private String address;
    @Field
    @PositiveOrZero
    private int capacity;
    @Field
    private  boolean operative;
    @Field(name = "medium_price")
    @PositiveOrZero
    private float mediumPrice;
    @Field
    @NotEmpty
    private String category;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

}
