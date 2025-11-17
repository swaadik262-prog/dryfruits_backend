package com.dryfruits.dryfruitsbackend.controller;

import com.dryfruits.dryfruitsbackend.Dao.ProductMenu;
import com.dryfruits.dryfruitsbackend.model.ProductVarieties;
import com.dryfruits.dryfruitsbackend.model.Products;
import com.dryfruits.dryfruitsbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProductForMenu")
    private List<ProductMenu> getAllProductsForMenu() {
        return productService.getAllProductForMenu();
    }

    @GetMapping("/getProductWithVariety")
    private List<Map<String,Object>> getProductWithVariety() {
        return productService.getAllProductWithVariety();
    }

    @PostMapping("/saveProduct")
    private void saveProduct(@RequestBody Products products) {
        productService.saveNewProduct(products);
    }

    @PutMapping("/updateProduct/{id}")
    private String updateProduct(@RequestBody Products products) {
        try {
            productService.updateProduct(products);
            return "Product updated successfully";
        } catch (Exception e) {
            return "Something went wrong";
        }
    }

    @DeleteMapping("/deleteProduct")
    private void deleteProduct(@RequestParam int id) {
        productService.deleteProduct(id);
    }

}