package org.apitestautomation.springboot.service;

import org.apitestautomation.springboot.exception.BadRequestException;
import org.apitestautomation.springboot.model.Order;
import org.springframework.stereotype.Service;
import org.testng.util.Strings;

import java.sql.Timestamp;

@Service
public class OrderService {
    private static final String NEW = "New";
    private static final String IN_PROGRESS = "InProgress";
    public Order ProcessOrder(Order order) {
        if (Strings.isNullOrEmpty(order.getId())){
            throw new BadRequestException("ORDER_ID_REQUIRED");
        }

        if (Strings.isNullOrEmpty(order.getStatus())){
            throw new BadRequestException("ORDER_STATUS_REQUIRED");
        }

        if (Boolean.FALSE.equals(order.getStatus().equals(NEW))) {
            throw new BadRequestException("ORDER_STATUS_IS_OTHER_THAN_NEW");
        }

        if (order.getStatus().equals(NEW)) {
            order.setStatus(IN_PROGRESS);
        }
        order.setLastUpdatedTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        return order;
    }
}
