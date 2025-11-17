package com.dryfruits.dryfruitsbackend.controller;

import com.dryfruits.dryfruitsbackend.model.ProductVarieties;
import com.dryfruits.dryfruitsbackend.service.ProductVarietiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/variety")
public class ProductVarietiesController {

    @Autowired
    private ProductVarietiesService productVarietiesService;

    @GetMapping("/getProductVariety")
    public List<ProductVarieties> getProductAllVariety() {
        return productVarietiesService.getProductAllVariety();
    }

    @GetMapping("/getProductVarietyByProductId")
    public List<ProductVarieties> getProductVarietyByProductId(@RequestParam int productId) {
        return productVarietiesService.getProductVarietyByProductId(productId);
    }

    @PostMapping("/saveProductVariety")
    public void saveProductVariety(@RequestBody List<ProductVarieties> productVarieties) {
        productVarietiesService.saveProductVariety(productVarieties);
    }

    @PutMapping("/updateProductVariety")
    public void updateProductVariety(@RequestBody ProductVarieties productVarieties) {
        productVarietiesService.updateProductVariety(productVarieties);
    }

    @DeleteMapping("/deleteProductVariety")
    public void deleteProductVariety(@RequestParam int id) {
        productVarietiesService.deleteProductVariety(id);
    }
}
