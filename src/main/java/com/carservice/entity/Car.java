package com.carservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;
    private String model;
    private int year;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @EqualsAndHashCode.Exclude //imported to fix error i had during debugging
    private Customer customer;

    @OneToMany(mappedBy = "car")
    @EqualsAndHashCode.Exclude 
    private List<ServiceRecord> serviceRecords;
}
