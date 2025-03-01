package com.suraj.NeoShop.dto;

import com.suraj.NeoShop.model.Category;

import java.math.BigDecimal;
import java.util.List;

public record ProductDto(Long id, String name, BigDecimal price, String brand, int quantity, String description, Category category, List<ImageDto> images

) {
}
