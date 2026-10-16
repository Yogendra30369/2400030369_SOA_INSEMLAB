package com.example.ordervalidation;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @PostMapping
    public Map<String, Object> createOrder(@Valid @RequestBody OrderRequest request) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message", "Order created successfully");
        response.put("customerName", request.getCustomerName());
        response.put("productName", request.getProductName());
        response.put("quantity", request.getQuantity());
        response.put("price", request.getPrice());
        return response;
    }
}
