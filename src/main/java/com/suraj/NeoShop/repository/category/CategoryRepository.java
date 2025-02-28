package com.suraj.NeoShop.repository.category;

import com.suraj.NeoShop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String reqCategory);
}
