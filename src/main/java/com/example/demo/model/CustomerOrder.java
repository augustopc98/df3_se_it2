package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer_orders")
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerEmail;
    private String customerAddress;
    private Date orderDate;
    private String deliveryStatus;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    public CustomerOrder(Long id, String customerEmail, String customerAddress, Date orderDate, List<OrderItem> items) {
        this.id = id;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.orderDate = orderDate;
        this.items = items;
        this.deliveryStatus = "NEW";  // Default status
    }

    public CustomerOrder() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    // Method to add an order item
    public void addOrderItem(OrderItem item) {
        items.add(item);
    }

    // Method to remove an order item
    public void removeOrderItem(OrderItem item) {
        items.remove(item);
    }

    // Method to calculate the total price of the order
    public BigDecimal calculateTotal() {
        return items.stream().map(OrderItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // Method to apply a discount to the order
    public void applyDiscount(Discount discount) {
        BigDecimal total = calculateTotal();
        BigDecimal discountAmount = discount.applyDiscount(total);
        // Logic to apply discount (you can store the discounted total if needed)
    }

    // Method to set the order as ready for delivery
    public void sendForDelivery() {
        this.deliveryStatus = "READY FOR DELIVERY";
    }

    // Method to update the delivery status
    public void updateDeliveryStatus(String status) {
        this.deliveryStatus = status;
    }
}
