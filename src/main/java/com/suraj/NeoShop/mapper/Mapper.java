package com.suraj.NeoShop.mapper;

import com.suraj.NeoShop.dto.ImageDto;
import com.suraj.NeoShop.dto.ProductDto;
import com.suraj.NeoShop.model.Image;
import com.suraj.NeoShop.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {

    /// product to productDto
    public static ProductDto convertToProductDto(Product product) {
        /// before save images convert to dto(if null then [] set it to empty)
        List<ImageDto> imageDto = (product.getImages() != null) ?
                product.getImages().stream().map(Mapper::convertToImageDto).toList() :
                new ArrayList<>();

        //return product dto
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getBrand(),
                product.getQuantity(),
                product.getDescription(),
                product.getCategory(),
                imageDto
        );
    }

    /// covert List of product to list of dto
    public static List<ProductDto> convertToListProductDto(List<Product> products) {
        return products.stream().map(Mapper::convertToProductDto).toList();
    }

    /// image to ImageDto
    public static ImageDto convertToImageDto(Image image) {
        return new ImageDto(
                image.getId(),
                image.getFileName(),
                image.getUrl()
        );
    }
}
