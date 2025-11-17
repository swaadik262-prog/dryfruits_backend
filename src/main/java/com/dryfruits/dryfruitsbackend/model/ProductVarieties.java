package com.dryfruits.dryfruitsbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ProductVarieties")
public class ProductVarieties {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int productId;
    private String name;
    private String imageUrl;
    private double rating;
    private int price;
    private String quality;
    private String about;
    private String benefits;

    private Date createdAt = new Date();
    private Date updatedAt;
    private Date deletedAt;
}
