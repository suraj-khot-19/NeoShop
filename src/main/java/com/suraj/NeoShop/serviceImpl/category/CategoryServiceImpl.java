package com.suraj.NeoShop.serviceImpl.category;

import com.suraj.NeoShop.model.Category;
import com.suraj.NeoShop.repository.CategoryRepository;
import com.suraj.NeoShop.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
