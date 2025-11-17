package com.dryfruits.dryfruitsbackend.repository;

import com.dryfruits.dryfruitsbackend.model.ProductVarieties;
import com.dryfruits.dryfruitsbackend.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ProductRepository extends JpaRepository<Products, Integer> {

    @Modifying
    @Query(value = "Update product set name =:productName, price =:productPrice, image =:productImage " +
            "updated_at =:updatedDate where id =:productId", nativeQuery = true)
    void updateSlider(int productId, String productName, int productPrice, String productImage, Date updatedDate);

    @Modifying
    @Query(value = "Update Product set deletedAt = now() where id =:id", nativeQuery = true)
    void deleteProduct(int id);

    @Query(value = "SELECT \n" +
            "  p.image, \n" +
            "  p.name, \n" +
            "  p.price, \n" +
            "  p.rating, \n" +
            "  p.sold, \n" +
            "  GROUP_CONCAT(pv.name SEPARATOR ', ') AS varieties\n" +
            "FROM product p\n" +
            "LEFT JOIN product_varieties pv ON pv.product_id = p.id\n" +
            "GROUP BY p.id", nativeQuery = true)
    List<Map<String, Object>> getAllProductWithVariety();

    @Query(value = "Select id, name from product where deleted_at Is Null", nativeQuery = true)
    List<Map<String,Object>> getAllProductForMenu();

}
