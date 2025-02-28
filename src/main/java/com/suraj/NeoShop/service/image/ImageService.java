package com.suraj.NeoShop.service.image;

import com.suraj.NeoShop.dto.ImageDto;
import com.suraj.NeoShop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> uploadImageForProduct(Long prodId, List<MultipartFile> files);
    ImageDto updateAnImage(Long id, MultipartFile file);
}
