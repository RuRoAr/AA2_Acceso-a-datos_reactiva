package com.svalero.deliveryAPI.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty
    private String dni;
    @Column
    @NotEmpty
    private String name;
    @Column
    @NotEmpty
    private String surname;
    @Column(name = "brith_date")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate birthDate;
    @Column
    @NotEmpty
    private String address;


    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders;

}
