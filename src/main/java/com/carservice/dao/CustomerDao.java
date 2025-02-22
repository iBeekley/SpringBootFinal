package com.carservice.dao;

import com.carservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
}
