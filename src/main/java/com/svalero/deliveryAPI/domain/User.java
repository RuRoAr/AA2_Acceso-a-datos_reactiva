package com.svalero.deliveryAPI.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "Users")
public class User {

    @Id
    private String id;
    @Field
    private String dni;
    @Field
    private String name;
    @Field
    private String surname;
    @Field(name = "brith_date")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate birthDate;
    @Field
    private String address;


    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
