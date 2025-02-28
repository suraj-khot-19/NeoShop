package com.suraj.NeoShop.serviceImpl.image;

import com.suraj.NeoShop.exception.ResourceNotFoundException;
import com.suraj.NeoShop.model.Image;
import com.suraj.NeoShop.repository.image.ImageRepository;
import com.suraj.NeoShop.service.image.ImageService;
import com.suraj.NeoShop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepo;
    private final ProductService productService;

    /// get image by id
    @Override
    public Image getImageById(Long id) {
        return imageRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image Not Found!"));
    }

    /// delete image by id
    @Override
    public void deleteImageById(Long id) {
        Image image=getImageById(id);
        imageRepo.delete(image);
    }
}
