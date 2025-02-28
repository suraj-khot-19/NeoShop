package com.suraj.NeoShop.controller.image;

import com.suraj.NeoShop.model.Image;
import com.suraj.NeoShop.response.SendResponse;
import com.suraj.NeoShop.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/neoshop/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService service;

    /// get image by id
    @GetMapping("/{id}")
    public ResponseEntity<SendResponse<Image>> getImageById(@PathVariable Long id) {
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Image Fetched Successfully!", service.getImageById(id)));
    }

    /// delete image by id
    @DeleteMapping("{id}")
    public ResponseEntity<SendResponse<Void>> deleteImageById(@PathVariable Long id) {
        service.deleteImageById(id);
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.NO_CONTENT, "Product Deleted Successfully!", null));
    }

    /// save image

    /// update image
}
