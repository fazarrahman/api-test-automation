package org.apitestautomation.springboot.controller;

import org.apitestautomation.springboot.model.Order;
import org.apitestautomation.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(
            value = "/order", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Order> createPerson(@RequestBody Order order) {
        return ResponseEntity.ok(orderService.ProcessOrder(order));
    }
}
