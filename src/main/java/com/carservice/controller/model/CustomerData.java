package com.carservice.controller.model;

import com.carservice.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class CustomerData {

    private Long customerId;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private Set<CarData> cars = new HashSet<>();

    public CustomerData(Customer customer) {
        this.customerId = customer.getCustomerId();
        this.customerFirstName = customer.getCustomerFirstName();
        this.customerLastName = customer.getCustomerLastName();
        this.customerEmail = customer.getCustomerEmail();

        customer.getCars().forEach(car -> this.cars.add(new CarData(car)));
    }
}
