package com.suraj.NeoShop.controller.product;

import com.suraj.NeoShop.model.Product;
import com.suraj.NeoShop.service.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/neoshop/product")
public class ProductController {

    ///  constructor dep injection
    ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    ///  get product by id
    @GetMapping("/{id}")
    private ResponseEntity<Product> getProductById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }
}
