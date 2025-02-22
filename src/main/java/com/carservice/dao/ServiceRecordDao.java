package com.carservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.carservice.entity.ServiceRecord;

public interface ServiceRecordDao extends JpaRepository<ServiceRecord, Long> {
}
