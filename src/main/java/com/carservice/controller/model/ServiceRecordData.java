package com.carservice.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.carservice.entity.ServiceRecord;
import java.util.Date;

@Data
@NoArgsConstructor
public class ServiceRecordData {

    private Long recordId;
    private Date date;
    private String description;
    private double cost;

    public ServiceRecordData(ServiceRecord serviceRecord) {
        this.recordId = serviceRecord.getId();
        this.date = serviceRecord.getDate();
        this.description = serviceRecord.getDescription();
        this.cost = serviceRecord.getCost();
    }
}
