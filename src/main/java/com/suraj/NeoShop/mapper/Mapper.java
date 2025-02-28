package com.suraj.NeoShop.mapper;

import com.suraj.NeoShop.dto.ImageDto;
import com.suraj.NeoShop.dto.ProductDto;
import com.suraj.NeoShop.model.Image;
import com.suraj.NeoShop.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    /// product to productDto
    public static ProductDto convertToProductDto(Product product, List<Image> images) {
        /// before save images convert to dto
        List<ImageDto> imageDto = images.stream()
                .map(Mapper::convertToImageDto)
                .toList();

        //return product dto
        return new ProductDto(
                product.getName(),
                product.getPrice(),
                product.getBrand(),
                product.getQuantity(),
                product.getDescription(),
                product.getCategory(),
                imageDto
        );
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
