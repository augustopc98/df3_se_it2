package com.example.demo.controller;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Discount;
import com.example.demo.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @PostMapping
    public CustomerOrder createOrder(@RequestParam String customerEmail, @RequestParam String customerAddress,
                                     @RequestBody List<OrderItem> items) {
        return customerOrderService.createOrder(customerEmail, customerAddress, new Date(), items);
    }

    @PostMapping("/{orderId}/items")
    public void addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem item) {
        customerOrderService.addOrderItem(orderId, item);
    }

    @DeleteMapping("/{orderId}/items")
    public void removeOrderItem(@PathVariable Long orderId, @RequestBody OrderItem item) {
        customerOrderService.removeOrderItem(orderId, item);
    }

    @GetMapping("/{orderId}/total")
    public BigDecimal calculateTotal(@PathVariable Long orderId) {
        return customerOrderService.calculateTotal(orderId);
    }

    @PostMapping("/{orderId}/discount")
    public void applyDiscount(@PathVariable Long orderId, @RequestBody Discount discount) {
        customerOrderService.applyDiscount(orderId, discount);
    }

    @PostMapping("/{orderId}/delivery")
    public void sendForDelivery(@PathVariable Long orderId) {
        customerOrderService.sendForDelivery(orderId);
    }

    @PostMapping("/{orderId}/status")
    public void updateDeliveryStatus(@PathVariable Long orderId, @RequestParam String status) {
        customerOrderService.updateDeliveryStatus(orderId, status);
    }

    @GetMapping("/{orderId}")
    public CustomerOrder getOrder(@PathVariable Long orderId) {
        return customerOrderService.getOrder(orderId);
    }

    @GetMapping
    public List<CustomerOrder> listOrders() {
        return customerOrderService.listOrders();
    }
}
