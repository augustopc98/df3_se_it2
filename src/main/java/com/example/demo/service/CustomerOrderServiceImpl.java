package com.example.demo.service;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Discount;
import com.example.demo.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Override
    public CustomerOrder createOrder(String customerEmail, String customerAddress, Date orderDate, List<OrderItem> items) {
        CustomerOrder order = new CustomerOrder(null, customerEmail, customerAddress, orderDate, items);
        return customerOrderRepository.save(order);
    }

    @Override
    public void addOrderItem(Long orderId, OrderItem item) {
        CustomerOrder order = customerOrderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.addOrderItem(item);
            customerOrderRepository.save(order);
        }
    }

    @Override
    public void removeOrderItem(Long orderId, OrderItem item) {
        CustomerOrder order = customerOrderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.removeOrderItem(item);
            customerOrderRepository.save(order);
        }
    }

    @Override
    public BigDecimal calculateTotal(Long orderId) {
        CustomerOrder order = customerOrderRepository.findById(orderId).orElse(null);
        return order != null ? order.calculateTotal() : BigDecimal.ZERO;
    }

    @Override
    public void applyDiscount(Long orderId, Discount discount) {
        CustomerOrder order = customerOrderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.applyDiscount(discount);
            customerOrderRepository.save(order);
        }
    }

    @Override
    public void sendForDelivery(Long orderId) {
        CustomerOrder order = customerOrderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.sendForDelivery();
            customerOrderRepository.save(order);
        }
    }

    @Override
    public void updateDeliveryStatus(Long orderId, String status) {
        CustomerOrder order = customerOrderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.updateDeliveryStatus(status);
            customerOrderRepository.save(order);
        }
    }

    @Override
    public CustomerOrder getOrder(Long orderId) {
        return customerOrderRepository.findById(orderId).orElse(null);
    }

    @Override
    public List<CustomerOrder> listOrders() {
        return customerOrderRepository.findAll();
    }
}
