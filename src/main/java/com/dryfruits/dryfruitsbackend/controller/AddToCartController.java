package com.dryfruits.dryfruitsbackend.controller;

import com.dryfruits.dryfruitsbackend.model.AddToCart;
import com.dryfruits.dryfruitsbackend.service.AddToCartService;
import jakarta.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
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
    public void saveInCart(@RequestBody AddToCart addToCart,
                           HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")){
            token = token.substring(7);
        }

        String[] parts = token.split("\\.");
        String payload = new String(Base64.getDecoder().decode(parts[1]));

        JSONObject jsonPayload = new JSONObject(payload);
        int userId = jsonPayload.getInt("userId");

        System.out.println("JSON PAYLOAD: "+jsonPayload);
        System.out.println("USER ID: "+userId);

        addToCartService.saveInCart(userId, addToCart);
    }
}