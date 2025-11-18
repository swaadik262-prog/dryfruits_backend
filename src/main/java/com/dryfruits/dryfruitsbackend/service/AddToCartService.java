package com.dryfruits.dryfruitsbackend.service;

import com.dryfruits.dryfruitsbackend.model.AddToCart;
import com.dryfruits.dryfruitsbackend.repository.AddToCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddToCartService {

    @Autowired
    private AddToCartRepository addToCartRepository;


    public List<AddToCart> getCartDetails(int userId) {
        return addToCartRepository.getCartDetailsByUserId(userId);
    }

    public void saveInCart(int userId, AddToCart addToCart) {
        addToCart.setUserId(userId);
        addToCartRepository.save(addToCart);
        System.out.println(userId);
    }
}
