package com.suraj.NeoShop.serviceImpl.category;

import com.suraj.NeoShop.exception.AlreadyExistsException;
import com.suraj.NeoShop.exception.ResourceNotFoundException;
import com.suraj.NeoShop.model.Category;
import com.suraj.NeoShop.repository.CategoryRepository;
import com.suraj.NeoShop.request.RequestCategory;
import com.suraj.NeoShop.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    /// constructor injection with @RequiredArgsConstructor annotation
    private final CategoryRepository categoryRepo;

    /// get all categories
    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    /// get cat by name
    @Override
    public Category getCategoryByName(String name) {
        return Optional.ofNullable(categoryRepo.findByName(name))
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found!"));
    }

    /// get cat by id
    @Override
    public Category getCategoryById(Long id) {
        return Optional.of(categoryRepo.findById(id))
                .get().orElseThrow(() -> new ResourceNotFoundException("Category Not Found!"));
    }

    /// add new cat
    @Override
    public Category addNewCategory(RequestCategory category) {
        Category createCategory = new Category();

        //check if already exists
        Category newCategory = categoryRepo.findByName(category.getName());

        //if not
        if (newCategory == null) {
            createCategory.setName(category.getName());
            return categoryRepo.save(createCategory);
        } else {
            throw new AlreadyExistsException("Category Already Exists!");
        }
    }
}
