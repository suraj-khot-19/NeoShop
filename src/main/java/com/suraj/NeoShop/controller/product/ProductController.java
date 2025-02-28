package com.suraj.NeoShop.controller.product;

import com.suraj.NeoShop.exception.ResourceNotFoundException;
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
    public ResponseEntity<SendResponse> getProductById(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(new SendResponse("Product Fetched Successfully!", service.getProductById(id)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse(e.getMessage(), null));
        }
    }

    /// add new product
    @PostMapping("/new")
    public ResponseEntity<SendResponse> addNewProduct(@RequestBody RequestProduct product) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new SendResponse("Product Added Successfully!", service.addProduct(product)));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SendResponse(e.getMessage(), null));
        }
    }

    /// delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<SendResponse> deleteProductById(@PathVariable(name = "id") Long id) {
        try {
            service.deleteProduct(id);
            return ResponseEntity.ok(new SendResponse("Product Deleted Successfully!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse(e.getMessage(), null));
        }
    }

    /// get all products
    @GetMapping("/all")
    public ResponseEntity<SendResponse> getAllProducts() {
        return ResponseEntity.ok(new SendResponse("Products Fetched Successfully!", service.getAllProducts()));
    }


    /// update an existing product
    @PutMapping("/update/{id}")
    public ResponseEntity<SendResponse> updateProduct(@RequestBody RequestProduct product, @PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(new SendResponse("Product Updated Successfully!", service.updateProduct(product, id)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse(e.getMessage(), null));
        }
    }

    /// get product by category name
    @GetMapping("/category/{category}")
    public ResponseEntity<SendResponse> getProductsByCategory(@PathVariable(name = "category") String name) {
        try {
            List<Product> products = service.getProductsByCategory(name);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse("No Products Found!", null));
            }
            return ResponseEntity.ok(new SendResponse("Products Fetched Successfully!", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SendResponse(e.getMessage(), null));
        }
    }

    /// get product by brand
    @GetMapping("/brand/{brand}")
    public ResponseEntity<SendResponse> getProductsByBrand(@PathVariable(name = "brand") String brand) {
        try {
            List<Product> products = service.getProductsByBrand(brand);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse("No Products Found!", null));
            }
            return ResponseEntity.ok(new SendResponse("Products Fetched Successfully!", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SendResponse(e.getMessage(), null));
        }
    }

    /// get products by brand and category
    @GetMapping("/category/{category}/brand/{brand}")
    public ResponseEntity<SendResponse> getProductsByCategoryNameAndBrand(@PathVariable(name = "category") String category, @PathVariable(name = "brand") String brand) {
        try {
            List<Product> products = service.getProductsByCategoryNameAndBrand(category, brand);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse("No Products Found!", null));
            }
            return ResponseEntity.ok(new SendResponse("Products Fetched Successfully!", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SendResponse(e.getMessage(), null));
        }
    }

    /// get products by name
    @GetMapping("/find/{name}")
    public ResponseEntity<SendResponse> getProductsByName(@PathVariable(name = "name") String name) {
        try {
            List<Product> products = service.getProductsByName(name);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse("Products Not Found!", null));
            }
            return ResponseEntity.ok(new SendResponse("Products Fetched Successfully!", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SendResponse(e.getMessage(), null));
        }
    }

    /// get products by name and brand
    @GetMapping("/find/{name}/brand/{brand}")
    public ResponseEntity<SendResponse> getProductsByNameAndBrand(@PathVariable(name = "name") String name, @PathVariable(name = "brand") String brand) {
        try {
            List<Product> products = service.getProductsByNameAndBrand(name, brand);
            if (products.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse("Products Not Found!", null));
            }
            return ResponseEntity.ok(new SendResponse("Products Fetched Successfully!", products));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SendResponse(e.getMessage(), null));
        }
    }
}
