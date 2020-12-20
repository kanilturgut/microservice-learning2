package com.kanilturgut.product.catalog.service.controller;

import com.kanilturgut.product.catalog.service.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("products")
public class ProductCatalogController {

    private static final Map<String, Product> productCatalog = new HashMap<>();

    @PostMapping()
    public String addProduct(@RequestBody Product product) {
        productCatalog.put(product.getId(), product);
        return "product added successfully";
    }

    @GetMapping("{id}")
    public Product getProductDetails(@PathVariable String id) {
        return productCatalog.get(id);
    }

    @GetMapping()
    public List<Product> getProductList() {
        return new ArrayList<>(productCatalog.values());
    }

    @PutMapping("{id}")
    public String updateProduct(@PathVariable String id, @RequestBody Product product) {
        productCatalog.put(id, product);
        return "product updated successfully";
    }

    @DeleteMapping("{id}")
    public String deleteProduct(@PathVariable String id) {
        productCatalog.remove(id);
        return "product deleted successfully";
    }
}
