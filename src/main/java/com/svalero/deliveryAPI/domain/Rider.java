package com.svalero.deliveryAPI.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "riders")
@Table(name = "riders")
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    @NotNull
    @Pattern(regexp = "[0-9]{8}[A-Z]")
    private String dni;
    @Column
    @NotNull
    @NotEmpty
    private String name;
    @Column
    @NotEmpty
    private String surname;
    @Column
    @NotEmpty
    private String vehicle;
    @Column
    @NotEmpty
    private int maxSpeed;

    @OneToMany(mappedBy = "rider")
    private List<Order> orders;
}
