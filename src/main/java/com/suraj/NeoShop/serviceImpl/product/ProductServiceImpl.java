package com.suraj.NeoShop.serviceImpl.product;

import com.suraj.NeoShop.exception.ResourceNotFoundException;
import com.suraj.NeoShop.model.Category;
import com.suraj.NeoShop.model.Product;
import com.suraj.NeoShop.repository.CategoryRepository;
import com.suraj.NeoShop.repository.ProductRepository;
import com.suraj.NeoShop.request.RequestProduct;
import com.suraj.NeoShop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    /// constructor dep injection by annotation *@RequiredArgsConstructor*
    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    /// get product by id
    @Override
    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not Found!"));
    }

    /// add a new product
    @Override
    public Product addProduct(RequestProduct product) {
        // checking if category is present or else create new
        String reqCategoryName = product.getCategory().getName();

        Category newCategory = Optional.ofNullable(categoryRepo.findByName(reqCategoryName)).orElseGet(
                () -> {
                    Category createCategory = new Category(reqCategoryName);
                    return categoryRepo.save(createCategory);
                }
        );

        Product newProduct = new Product(
                product.getName(),
                product.getPrice(),
                product.getBrand(),
                product.getQuantity(),
                product.getDescription(),
                newCategory
        );
        return productRepo.save(newProduct);
    }


    /// delete a product
    @Override
    public void deleteProduct(Long id) {
        Optional.ofNullable(getProductById(id))
                .ifPresentOrElse(
                        product -> productRepo.deleteById(id),
                        () -> {
                            throw new ResourceNotFoundException("Product Not Found With Id: " + id);
                        });
    }


    /// update an existing product
    @Override
    public Product updateProduct(RequestProduct requestProduct, Long id) {
        //first check whether product exists or not
        return Optional.ofNullable(getProductById(id)).map(
                (existingProduct) -> {
                    existingProduct.setName(requestProduct.getName());
                    existingProduct.setBrand(requestProduct.getBrand());
                    existingProduct.setPrice(requestProduct.getPrice());
                    existingProduct.setDescription(requestProduct.getDescription());

                    //check for category if present then add or else create
                    String categoryName = requestProduct.getCategory().getName();

                    Category newCategory = Optional.ofNullable(categoryRepo.findByName(categoryName)).orElseGet(() -> {
                        Category createCategory = new Category(categoryName);
                        return categoryRepo.save(createCategory);
                    });

                    existingProduct.setCategory(newCategory);

                    return productRepo.save(existingProduct);
                }).orElseThrow(() -> new ResourceNotFoundException("Product Not Found!"));
    }


    /// get all products
    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    
    /// get products by category
    @Override
    public List<Product> getProductsByCategory(String name) {
        /// check for category exists
        Optional.ofNullable(categoryRepo.findByName(name)).orElseThrow(() -> new ResourceNotFoundException("Category Not Found!"));
        
       return productRepo.findByCategoryName(name);
    }
}
