package com.carservice.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.carservice.entity.Car;

@Data
@NoArgsConstructor
public class CarData {

    private Long carId;
    private String make;
    private String model;
    private int year;

    // Constructor to map from Car entity to CarData DTO
    public CarData(Car car) {
        this.carId = car.getId();
        this.make = car.getMake();
        this.model = car.getModel();
        this.year = car.getYear();
    }
}
