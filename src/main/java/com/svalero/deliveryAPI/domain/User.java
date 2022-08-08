package com.svalero.deliveryAPI.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Users")
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String dni;
    @Column
    private String name;
    @Column
    private String surname;
    @Column(name = "brith_date")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate birthDate;
    @Column
    private String address;


    @OneToMany(mappedBy = "user")
    private List<Order> orders;

}
