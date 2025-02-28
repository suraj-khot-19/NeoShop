package com.suraj.NeoShop.controller.category;

import com.suraj.NeoShop.exception.AlreadyExistsException;
import com.suraj.NeoShop.exception.ResourceNotFoundException;
import com.suraj.NeoShop.model.Category;
import com.suraj.NeoShop.request.RequestCategory;
import com.suraj.NeoShop.response.SendResponse;
import com.suraj.NeoShop.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/neoshop/category")
public class CategoryController {
    /// constructor injection with @RequiredArgsConstructor annotation
    private final CategoryService service;

    /// get all categories
    @GetMapping("/all")
    public ResponseEntity<SendResponse> getAllCategories() {
        return ResponseEntity.ok(new SendResponse("Fetched All Categories!", service.getAllCategories()));
    }

    /// get cat by name
    @GetMapping("/find/{name}")
    public ResponseEntity<SendResponse> getCategoryByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(new SendResponse("Fetched Category Successfully!", service.getCategoryByName(name)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse(e.getMessage(), null));
        }
    }

    /// get category by id
    @GetMapping("/{id}")
    public ResponseEntity<SendResponse> getCategoryById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new SendResponse("Fetched Category Successfully!", service.getCategoryById(id)));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new SendResponse(e.getMessage(), null));
        }
    }

    /// add new category
    @PostMapping("/new")
    public ResponseEntity<SendResponse> addNewCategory(@RequestBody RequestCategory category) {
        return ResponseEntity.ok(new SendResponse("Category Created Successfully!", service.addNewCategory(category)));
    }
}
