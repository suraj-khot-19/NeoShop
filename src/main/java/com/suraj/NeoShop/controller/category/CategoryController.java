package com.suraj.NeoShop.controller.category;

import com.suraj.NeoShop.response.SendResponse;
import com.suraj.NeoShop.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
