package com.dryfruits.dryfruitsbackend.controller;

import com.dryfruits.dryfruitsbackend.model.AddToCart;
import com.dryfruits.dryfruitsbackend.service.AddToCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addToCart")
public class AddToCartController {

    @Autowired
    private AddToCartService addToCartService;

    @GetMapping("/getCart/{userId}")
    public List<AddToCart> getCartDetails(@PathVariable ("userId") int userId) {
        return addToCartService.getCartDetails(userId);
    }

    @PostMapping("/saveInCart")
    public void saveInCart(@RequestBody AddToCart addToCart) {
        addToCartService.saveInCart(addToCart);
    }
}
