package com.dryfruits.dryfruitsbackend.repository;

import com.dryfruits.dryfruitsbackend.model.ProductVarieties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductVarietiesRepository extends JpaRepository<ProductVarieties, Integer> {

    @Modifying
    @Query(value = "Update product_varieties set name =:productVarietyName, price =:productVarietyPrice, rating =:productVarietyRating " +
            "where id =:productVarietyId", nativeQuery = true)
    void updateProductVariety(int productVarietyId, String productVarietyName, int productVarietyPrice, double productVarietyRating);

    @Modifying
    @Query(value = "Update product_varieties set deletedAt = now() where id =:id", nativeQuery = true)
    void deleteProductById(int id);

    @Query(value = "Select * from product_varieties where product_id =:productId", nativeQuery = true)
    List<ProductVarieties> getProductVarietyByProductId(int productId);

}
