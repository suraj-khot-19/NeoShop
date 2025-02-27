package com.suraj.NeoShop.controller.product;

import com.suraj.NeoShop.model.Product;
import com.suraj.NeoShop.request.RequestProduct;
import com.suraj.NeoShop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /// get all products
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }


    /// update an existing product
    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody RequestProduct product, @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(service.updateProduct(product, id));
    }

    /// get product by category name
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable(name = "category") String name) {
        return ResponseEntity.ok(service.getProductsByCategory(name));
    }

    /// get product by brand
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable(name = "brand") String brand) {
        return ResponseEntity.ok(service.getProductsByBrand(brand));
    }

    /// get products by brand and category
    @GetMapping("/category/{category}/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByCategoryNameAndBrand(@PathVariable(name = "category") String category, @PathVariable(name = "brand") String brand) {
        return ResponseEntity.ok(service.getProductsByCategoryNameAndBrand(category, brand));
    }
}
