package com.suraj.NeoShop.serviceImpl.product;

import com.suraj.NeoShop.exception.ProductNotFoundException;
import com.suraj.NeoShop.model.Product;
import com.suraj.NeoShop.repository.ProductRepository;
import com.suraj.NeoShop.service.product.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    /// constructor dep injection
    ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    /// get product by id
    @Override
    public Product getProductById(Long id) {
        return repo.findById(id).orElseThrow(()->new ProductNotFoundException("Product Not Found"));
    }
}
