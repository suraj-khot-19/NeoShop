package com.suraj.NeoShop.service.product;

import com.suraj.NeoShop.model.Product;
import com.suraj.NeoShop.request.RequestProduct;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    Product addProduct(RequestProduct product);
    void deleteProduct(Long id);
    Product updateProduct(Product product);
    List<Product> getAllProduct();
}
