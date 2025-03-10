package com.suraj.NeoShop.controller.product;

import com.suraj.NeoShop.dto.ProductDto;
import com.suraj.NeoShop.mapper.Mapper;
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
    public ResponseEntity<SendResponse<ProductDto>> getProductById(@PathVariable Long id) {
        Product product = service.getProductById(id);
        ProductDto productDto = Mapper.convertToProductDto(product);

        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Product Fetched Successfully!", productDto));
    }

    /// add new product
    @PostMapping("/new")
    public ResponseEntity<SendResponse<ProductDto>> addNewProduct(@RequestBody RequestProduct product) {
        Product newProduct = service.addProduct(product);
        ProductDto productDto = Mapper.convertToProductDto(newProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(new SendResponse<>(HttpStatus.CREATED, "Product Added Successfully!", productDto));
    }

    /// delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<SendResponse<Void>> deleteProductById(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SendResponse<>(HttpStatus.NO_CONTENT, "Product Deleted Successfully!", null));
    }

    /// get all products
    @GetMapping("/all")
    public ResponseEntity<SendResponse<List<ProductDto>>> getAllProducts() {
        List<Product> products = service.getAllProducts();
        List<ProductDto> productDto = Mapper.convertToListProductDto(products);

        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse<>(HttpStatus.NOT_FOUND, "No Products In DB!", productDto));
        }
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Products Fetched Successfully!", productDto));
    }


    /// update an existing product
    @PutMapping("/update/{id}")
    public ResponseEntity<SendResponse<ProductDto>> updateProduct(@RequestBody RequestProduct product, @PathVariable Long id) {
        Product updateProduct = service.updateProduct(product, id);
        ProductDto productDto = Mapper.convertToProductDto(updateProduct);

        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Product Updated Successfully!", productDto));
    }

    /// get products by category name
    @GetMapping("/category/{category}")
    public ResponseEntity<SendResponse<List<ProductDto>>> getProductsByCategory(@PathVariable(name = "category") String name) {
        List<Product> products = service.getProductsByCategory(name);
        List<ProductDto> productDto = Mapper.convertToListProductDto(products);
        if (productDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse<>(HttpStatus.NOT_FOUND, "No Products Found!", null));
        }
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Products Fetched Successfully!", productDto));
    }

    /// get products by brand
    @GetMapping("/brand/{brand}")
    public ResponseEntity<SendResponse<List<ProductDto>>> getProductsByBrand(@PathVariable String brand) {
        List<Product> products = service.getProductsByBrand(brand);
        List<ProductDto> productDto = Mapper.convertToListProductDto(products);
        if (productDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse<>(HttpStatus.NOT_FOUND, "No Products Found!", null));
        }
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Products Fetched Successfully!", productDto));

    }

    /// get products by brand and category
    @GetMapping("/category/{category}/brand/{brand}")
    public ResponseEntity<SendResponse<List<ProductDto>>> getProductsByCategoryNameAndBrand(@PathVariable(name = "category") String category, @PathVariable(name = "brand") String brand) {
        List<Product> products = service.getProductsByCategoryNameAndBrand(category, brand);
        List<ProductDto> productDto = Mapper.convertToListProductDto(products);
        if (productDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse<>(HttpStatus.NOT_FOUND, "No Products Found!", null));
        }
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Products Fetched Successfully!", productDto));
    }

    /// get products by name
    @GetMapping("/find/{name}")
    public ResponseEntity<SendResponse<List<ProductDto>>> getProductsByName(@PathVariable String name) {
        List<Product> products = service.getProductsByName(name);
        List<ProductDto> productDto = Mapper.convertToListProductDto(products);
        if (productDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse<>(HttpStatus.NOT_FOUND, "Products Not Found!", null));
        }
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Products Fetched Successfully!", productDto));
    }

    /// get products by name and brand
    @GetMapping("/find/{name}/brand/{brand}")
    public ResponseEntity<SendResponse<List<ProductDto>>> getProductsByNameAndBrand(@PathVariable(name = "name") String name, @PathVariable(name = "brand") String brand) {
        List<Product> products = service.getProductsByNameAndBrand(name, brand);
        List<ProductDto> productDto = Mapper.convertToListProductDto(products);
        if (productDto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse<>(HttpStatus.NOT_FOUND, "Products Not Found!", null));
        }
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Products Fetched Successfully!", productDto));
    }
}
