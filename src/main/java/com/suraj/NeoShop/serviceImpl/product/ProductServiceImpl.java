package com.suraj.NeoShop.serviceImpl.product;

import com.suraj.NeoShop.exception.ProductNotFoundException;
import com.suraj.NeoShop.model.Category;
import com.suraj.NeoShop.model.Product;
import com.suraj.NeoShop.repository.ProductRepository;
import com.suraj.NeoShop.request.RequestProduct;
import com.suraj.NeoShop.service.product.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    /// constructor dep injection
    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    /// get product by id
    @Override
    public Product getProductById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found!"));
    }

    /// add a new product
    @Override
    public Product addProduct(RequestProduct product) {
        Product newProduct = new Product(
                product.getName(),
                product.getPrice(),
                product.getBrand(),
                product.getQuantity(),
                product.getDescription()
        );
        //TODO
        Category category=product.getCategory();

        return null;
    }


    /// delete a product
    @Override
    public void deleteProduct(Long id) {
        Optional.ofNullable(getProductById(id))
                .ifPresentOrElse(
                        product -> repo.deleteById(id),
                        () -> {
                            throw new ProductNotFoundException("Product Not Found With Id: " + id);
                        });
    }


    /// update an existing product
    @Override
    public Product updateProduct(Product product) {
        return null;
    }


    /// get all products
    @Override
    public List<Product> getAllProduct() {
        return repo.findAll();
    }
}
