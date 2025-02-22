package com.carservice.controller;

import com.carservice.controller.model.CustomerData;

import com.carservice.controller.model.CarData;
import com.carservice.controller.model.ServiceRecordData;
import com.carservice.service.CarServiceService;

import lombok.extern.slf4j.Slf4j;
import com.carservice.controller.CustomerController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CarServiceService carServiceService; 
    
    public CustomerController(CarServiceService carServiceService) {
        this.carServiceService = carServiceService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerData> addCustomer(@RequestBody CustomerData customerData) {
        log.info("Received request to add a new customer: {}", customerData);
        CustomerData savedCustomer = carServiceService.saveCustomer(null, customerData);
        return ResponseEntity.ok(savedCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerData> updateCustomer(@PathVariable Long id, @RequestBody CustomerData customerData) {
        log.info("Updating customer with ID: {}", id);
        CustomerData updatedCustomerData = carServiceService.saveCustomer(id, customerData);
        return ResponseEntity.ok(updatedCustomerData);
    }

    @GetMapping
    public ResponseEntity<List<CustomerData>> getAllCustomers() {
        return ResponseEntity.ok(carServiceService.retrieveAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerData> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(carServiceService.retrieveCustomerById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        carServiceService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{customerId}/cars")
    public ResponseEntity<CarData> addCarToCustomer(@PathVariable Long customerId, @RequestBody CarData carData) {
        CarData savedCar = carServiceService.saveCar(customerId, carData);
        return ResponseEntity.ok(savedCar);
    }

    @PostMapping("/{customerId}/cars/{carId}/service-records")
    public ResponseEntity<ServiceRecordData> addServiceRecordToCar(
            @PathVariable Long customerId, 
            @PathVariable Long carId, 
            @RequestBody ServiceRecordData serviceRecordData) {
        ServiceRecordData savedServiceRecord = carServiceService.saveServiceRecord(carId, serviceRecordData);
        return ResponseEntity.ok(savedServiceRecord);
    }
}
