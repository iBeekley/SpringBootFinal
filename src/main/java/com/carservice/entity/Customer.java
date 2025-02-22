package com.carservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Car> cars = new HashSet<>();
}

