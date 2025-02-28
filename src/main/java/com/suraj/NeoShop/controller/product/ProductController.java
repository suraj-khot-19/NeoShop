package com.suraj.NeoShop.controller.product;

import com.suraj.NeoShop.model.Product;
import com.suraj.NeoShop.request.RequestProduct;
import com.suraj.NeoShop.response.SendResponse;
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
    ///  constructor dep injection by annotation @RequiredArgsConstructor
    private final ProductService service;

    ///  get product by id
    @GetMapping("/{id}")
    public ResponseEntity<SendResponse<Product>> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(new SendResponse<Product>(HttpStatus.OK, "Product Fetched Successfully!", service.getProductById(id)));
    }

    /// add new product
    @PostMapping("/new")
    public ResponseEntity<SendResponse<Product>> addNewProduct(@RequestBody RequestProduct product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new SendResponse<>(HttpStatus.CREATED, "Product Added Successfully!", service.addProduct(product)));
    }

    /// delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<SendResponse<Void>> deleteProductById(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SendResponse<>(HttpStatus.NO_CONTENT, "Product Deleted Successfully!", null));
    }

    /// get all products
    @GetMapping("/all")
    public ResponseEntity<SendResponse<List<Product>>> getAllProducts() {
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Products Fetched Successfully!", service.getAllProducts()));
    }


    /// update an existing product
    @PutMapping("/update/{id}")
    public ResponseEntity<SendResponse<Product>> updateProduct(@RequestBody RequestProduct product, @PathVariable Long id) {
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Product Updated Successfully!", service.updateProduct(product, id)));
    }

    /// get products by category name
    @GetMapping("/category/{category}")
    public ResponseEntity<SendResponse<List<Product>>> getProductsByCategory(@PathVariable String name) {
        List<Product> products = service.getProductsByCategory(name);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse<>(HttpStatus.NOT_FOUND, "No Products Found!", null));
        }
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Products Fetched Successfully!", products));

    }

    /// get products by brand
    @GetMapping("/brand/{brand}")
    public ResponseEntity<SendResponse<List<Product>>> getProductsByBrand(@PathVariable String brand) {
        List<Product> products = service.getProductsByBrand(brand);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse<>(HttpStatus.NOT_FOUND, "No Products Found!", null));
        }
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Products Fetched Successfully!", products));

    }

    /// get products by brand and category
    @GetMapping("/category/{category}/brand/{brand}")
    public ResponseEntity<SendResponse<List<Product>>> getProductsByCategoryNameAndBrand(@PathVariable(name = "category") String category, @PathVariable(name = "brand") String brand) {
        List<Product> products = service.getProductsByCategoryNameAndBrand(category, brand);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse<>(HttpStatus.NOT_FOUND, "No Products Found!", null));
        }
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Products Fetched Successfully!", products));

    }

    /// get products by name
    @GetMapping("/find/{name}")
    public ResponseEntity<SendResponse<List<Product>>> getProductsByName(@PathVariable String name) {
        List<Product> products = service.getProductsByName(name);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse<>(HttpStatus.NOT_FOUND, "Products Not Found!", null));
        }
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Products Fetched Successfully!", products));
    }

    /// get products by name and brand
    @GetMapping("/find/{name}/brand/{brand}")
    public ResponseEntity<SendResponse<List<Product>>> getProductsByNameAndBrand(@PathVariable(name = "name") String name, @PathVariable(name = "brand") String brand) {
        List<Product> products = service.getProductsByNameAndBrand(name, brand);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse<>(HttpStatus.NOT_FOUND, "Products Not Found!", null));
        }
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Products Fetched Successfully!", products));

    }
}
