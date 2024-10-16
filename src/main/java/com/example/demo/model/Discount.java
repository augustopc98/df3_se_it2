package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal discountPercentage;

    public Discount(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Discount() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal applyDiscount(BigDecimal totalAmount) {
        return totalAmount.multiply(discountPercentage).divide(BigDecimal.valueOf(100));
    }
}
