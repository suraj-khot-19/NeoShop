package com.suraj.NeoShop.service.category;

import com.suraj.NeoShop.model.Category;
import com.suraj.NeoShop.request.RequestCategory;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryByName(String name);
    Category getCategoryById(Long id);
    Category addNewCategory(RequestCategory category);
    void deleteCategoryById(Long id);
    Category updateAnExistingCategory(Long id, RequestCategory category);
}
