package com.suraj.NeoShop.controller.product;

import com.suraj.NeoShop.model.Product;
import com.suraj.NeoShop.request.RequestProduct;
import com.suraj.NeoShop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/neoshop/product")
public class ProductController {
    ///  constructor dep injection
    private final ProductService service;

    ///  get product by id
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    /// add new product
    @PostMapping("/new")
    public ResponseEntity<Product> addNewProduct(@RequestBody RequestProduct product) {
        return new ResponseEntity<>(service.addProduct(product), HttpStatus.CREATED);
    }

    /// delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable(name = "id") Long id) {
        service.deleteProduct(id);
        return ResponseEntity.ok("Product Deleted Successfully");
    }
}
