package com.dryfruits.dryfruitsbackend.service;

import com.dryfruits.dryfruitsbackend.Dao.ProductMenu;
import com.dryfruits.dryfruitsbackend.model.Products;
import com.dryfruits.dryfruitsbackend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductMenu> getAllProductForMenu() {
        List<Map<String,Object>> productForMenuList = productRepository.getAllProductForMenu();
        List<ProductMenu> productMenuNewList = new ArrayList<>();
        for (Map<String,Object> productForMenu : productForMenuList) {
            ProductMenu productMenu = new ProductMenu();
            productMenu.setId((Integer) productForMenu.get("id"));
            productMenu.setName((String) productForMenu.get("name"));

            productMenuNewList.add(productMenu);
        }
        return productMenuNewList;
    }

    public void saveNewProduct(Products products) {
        productRepository.save(products);
    }

    public void updateProduct(Products products) {
        productRepository.updateSlider(products.getId(), products.getName(), products.getPrice(),
                products.getImage(), new Date());
    }

    public void deleteProduct(int id) {
        productRepository.deleteProduct(id);
    }

    public List<Map<String, Object>> getAllProductWithVariety() {
        return productRepository.getAllProductWithVariety();
    }

}
