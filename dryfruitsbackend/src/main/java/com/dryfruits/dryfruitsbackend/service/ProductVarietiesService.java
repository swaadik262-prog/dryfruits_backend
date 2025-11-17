package com.dryfruits.dryfruitsbackend.service;

import com.dryfruits.dryfruitsbackend.model.ProductVarieties;
import com.dryfruits.dryfruitsbackend.repository.ProductVarietiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVarietiesService {

    @Autowired
    private ProductVarietiesRepository productVarietiesRepository;

    public List<ProductVarieties> getProductAllVariety() {
        return productVarietiesRepository.findAll();
    }

    public void saveProductVariety(List<ProductVarieties> productVarieties) {
        productVarietiesRepository.saveAll(productVarieties);
    }

    public void updateProductVariety(ProductVarieties productVarieties) {
        productVarietiesRepository.updateProductVariety(productVarieties.getId(), productVarieties.getName(),
                productVarieties.getPrice(), productVarieties.getRating());
    }

    public void deleteProductVariety(int id) {
        productVarietiesRepository.deleteProductById(id);
    }

    public List<ProductVarieties> getProductVarietyByProductId(int productId) {
        List<ProductVarieties> productWithVariety = productVarietiesRepository.getProductVarietyByProductId(productId);
        return productWithVariety;
    }
}
