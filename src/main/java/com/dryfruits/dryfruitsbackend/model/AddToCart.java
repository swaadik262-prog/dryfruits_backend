package com.dryfruits.dryfruitsbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "AddToCart")
public class AddToCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int userId;
    private int productId;
    private int price;
    private String image;
    private int quantity;
    private int unitPrice;

    private Date createdAt = new Date();
    private Date updatedAt;
    private Date deletedAt;
}
