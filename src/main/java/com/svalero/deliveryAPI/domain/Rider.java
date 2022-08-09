package com.svalero.deliveryAPI.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "riders")
public class Rider {
    @Id
    private String id;
    @Field
    @NotNull
    @Pattern(regexp = "[0-9]{8}[A-Z]")
    private String dni;
    @Field
    @NotNull
    @NotEmpty
    private String name;
    @Field
    private String surname;
    @Field
    private String vehicle;
    @Field
    private int maxSpeed;

    @OneToMany(mappedBy = "rider")
    private List<Order> orders;
}
