package com.example.demo.service;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Discount;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface CustomerOrderService {
    CustomerOrder createOrder(String customerEmail, String customerAddress, Date orderDate, List<OrderItem> items);
    void addOrderItem(Long orderId, OrderItem item);
    void removeOrderItem(Long orderId, OrderItem item);
    BigDecimal calculateTotal(Long orderId);
    void applyDiscount(Long orderId, Discount discount);
    void sendForDelivery(Long orderId);
    void updateDeliveryStatus(Long orderId, String status);
    CustomerOrder getOrder(Long orderId);
    List<CustomerOrder> listOrders();
}
