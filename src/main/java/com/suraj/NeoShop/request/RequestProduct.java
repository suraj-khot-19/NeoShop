package com.suraj.NeoShop.request;

import com.suraj.NeoShop.model.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class RequestProduct {
    private String name;
    private BigDecimal price;
    private String brand;
    private int quantity;
    private String description;
    private Category category;
}
