package com.suraj.NeoShop.service.image;

import com.suraj.NeoShop.model.Image;

public interface ImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
}
