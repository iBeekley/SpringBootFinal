package com.carservice.service;

import com.carservice.controller.model.CustomerData;
import com.carservice.controller.model.ServiceRecordData;
import com.carservice.controller.model.CarData;
import com.carservice.dao.CustomerDao;
import com.carservice.dao.ServiceRecordDao;
import com.carservice.dao.CarDao;
import com.carservice.entity.Customer;
import com.carservice.entity.ServiceRecord;
import com.carservice.entity.Car;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class CarServiceService {

    private final CustomerDao customerDao;
    private final CarDao carDao;
    private final ServiceRecordDao serviceRecordDao;

    public CarServiceService(CustomerDao customerDao, CarDao carDao, ServiceRecordDao serviceRecordDao) {
        this.customerDao = customerDao;
        this.carDao = carDao;
        this.serviceRecordDao = serviceRecordDao;
    }

    @Transactional
    public CustomerData saveCustomer(Long customerId, CustomerData customerData) {
        Customer customer = findOrCreateCustomer(customerId);
        copyCustomerFields(customer, customerData);
        customer = customerDao.save(customer);
        return new CustomerData(customer);
    }

    private void copyCustomerFields(Customer customer, CustomerData customerData) {
        customer.setCustomerFirstName(customerData.getCustomerFirstName());
        customer.setCustomerLastName(customerData.getCustomerLastName());
        customer.setCustomerEmail(customerData.getCustomerEmail());
    }

    private Customer findOrCreateCustomer(Long customerId) {
        return Objects.isNull(customerId) ? new Customer() : findCustomerById(customerId);
    }

    private Customer findCustomerById(Long customerId) {
        return customerDao.findById(customerId)
                .orElseThrow(() -> new NoSuchElementException("Customer with ID " + customerId + " not found."));
    }

    @Transactional
    public CarData saveCar(Long customerId, CarData carData) {
        Customer customer = findCustomerById(customerId);
        Long carId = carData.getCarId();
        Car car = findOrCreateCar(carId);

        copyCarFields(car, carData);
        car.setCustomer(customer);
        customer.getCars().add(car);

        return new CarData(carDao.save(car));
    }

    private void copyCarFields(Car car, CarData carData) {
        car.setMake(carData.getMake());
        car.setModel(carData.getModel());
        car.setYear(carData.getYear());
    }

    private Car findOrCreateCar(Long carId) {
        return Objects.isNull(carId) ? new Car() : findCarById(carId);
    }

    private Car findCarById(Long carId) {
        return carDao.findById(carId)
                .orElseThrow(() -> new NoSuchElementException("Car with ID " + carId + " not found."));
    }

    @Transactional
    public ServiceRecordData saveServiceRecord(Long carId, ServiceRecordData serviceRecordData) {
        Car car = findCarById(carId);
        Long recordId = serviceRecordData.getRecordId();
        ServiceRecord serviceRecord = findOrCreateServiceRecord(recordId);

        copyServiceRecordFields(serviceRecord, serviceRecordData);
        serviceRecord.setCar(car);
        car.getServiceRecords().add(serviceRecord);

        return new ServiceRecordData(serviceRecordDao.save(serviceRecord));
    }

    private void copyServiceRecordFields(ServiceRecord serviceRecord, ServiceRecordData serviceRecordData) {
        serviceRecord.setDate(serviceRecordData.getDate());
        serviceRecord.setDescription(serviceRecordData.getDescription());
        serviceRecord.setCost(serviceRecordData.getCost());
    }

    private ServiceRecord findOrCreateServiceRecord(Long recordId) {
        return Objects.isNull(recordId) ? new ServiceRecord() : findServiceRecordById(recordId);
    }

    private ServiceRecord findServiceRecordById(Long recordId) {
        return serviceRecordDao.findById(recordId)
                .orElseThrow(() -> new NoSuchElementException("Service Record with ID " + recordId + " not found."));
    }

    @Transactional
    public List<CustomerData> retrieveAllCustomers() {
        List<Customer> customers = customerDao.findAll();
        List<CustomerData> result = new LinkedList<>();
        for (Customer customer : customers) {
            CustomerData data = new CustomerData(customer);
            data.getCars().clear();
            result.add(data);
        }
        return result;
    }

    @Transactional
    public CustomerData retrieveCustomerById(Long customerId) {
        return new CustomerData(findCustomerById(customerId));
    }

    @Transactional
    public void deleteCustomerById(Long customerId) {
        Customer customer = findCustomerById(customerId);
        customerDao.delete(customer);
    }
}
