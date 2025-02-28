package com.suraj.NeoShop.controller.category;

import com.suraj.NeoShop.model.Category;
import com.suraj.NeoShop.model.Product;
import com.suraj.NeoShop.request.RequestCategory;
import com.suraj.NeoShop.response.SendResponse;
import com.suraj.NeoShop.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/neoshop/category")
public class CategoryController {
    /// constructor injection with @RequiredArgsConstructor annotation
    private final CategoryService service;

    /// get all categories
    @GetMapping("/all")
    public ResponseEntity<SendResponse<List<Category>>> getAllCategories() {
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Fetched All Categories!", service.getAllCategories()));
    }

    /// get cat by name
    @GetMapping("/find/{name}")
    public ResponseEntity<SendResponse<Category>> getCategoryByName(@PathVariable String name) {
        return ResponseEntity.ok(new SendResponse<Category>(HttpStatus.OK, "Fetched Category Successfully!", service.getCategoryByName(name)));
    }

    /// get category by id
    @GetMapping("/{id}")
    public ResponseEntity<SendResponse<Category>> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Fetched Category Successfully!", service.getCategoryById(id)));
    }

    /// add new category
    @PostMapping("/new")
    public ResponseEntity<SendResponse<Category>> addNewCategory(@RequestBody RequestCategory category) {
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.CREATED, "Category Created Successfully!", service.addNewCategory(category)));
    }

    /// delete a category by id
    @DeleteMapping("/{id}")
    public ResponseEntity<SendResponse<Void>> deleteCategoryById(@PathVariable Long id) {
        service.deleteCategoryById(id);
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.NO_CONTENT, "Category Deleted Successfully!", null));
    }


    /// update a category
    @PutMapping("/{id}")
    public ResponseEntity<SendResponse<Category>> updateAnExistingCategory(@PathVariable Long id, @RequestBody RequestCategory category) {
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Product Updated Successfully", service.updateAnExistingCategory
                (id, category)));
    }
}
