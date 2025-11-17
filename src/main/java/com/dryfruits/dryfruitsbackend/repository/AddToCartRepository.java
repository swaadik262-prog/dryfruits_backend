package com.dryfruits.dryfruitsbackend.repository;

import com.dryfruits.dryfruitsbackend.model.AddToCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddToCartRepository extends JpaRepository<AddToCart, Integer> {

    @Query(value = "select * from addtocart where user_id =:userId and deleted_at Is Null", nativeQuery = true)
    List<AddToCart> getCartDetailsByUserId(int userId);
}
