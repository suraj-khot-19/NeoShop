package com.suraj.NeoShop.controller.image;

import com.suraj.NeoShop.dto.ImageDto;
import com.suraj.NeoShop.mapper.Mapper;
import com.suraj.NeoShop.model.Image;
import com.suraj.NeoShop.response.SendResponse;
import com.suraj.NeoShop.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/neoshop/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService service;

    /// get image by id
    @GetMapping("/{id}")
    public ResponseEntity<SendResponse<ImageDto>> getImageById(@PathVariable Long id) {
        Image image = service.getImageById(id);
        ImageDto imageDto = Mapper.convertToImageDto(image);
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Image Fetched Successfully!", imageDto));
    }

    /// delete image by id
    @DeleteMapping("{id}")
    public ResponseEntity<SendResponse<Void>> deleteImageById(@PathVariable Long id) {
        service.deleteImageById(id);
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.NO_CONTENT, "Image Deleted Successfully!", null));
    }

    /// save image
    @PostMapping("/upload/{prodId}")
    public ResponseEntity<SendResponse<List<ImageDto>>> uploadImageForProduct(@PathVariable Long prodId, @RequestBody List<MultipartFile> files) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new SendResponse<>(HttpStatus.CREATED, "Image Uploaded Successfully!", service.uploadImageForProduct(prodId, files)));
    }

    /// update image
    @PutMapping("/update/{id}")
    public ResponseEntity<SendResponse<ImageDto>> updateAnImage(@PathVariable Long id, @RequestBody MultipartFile file) {
        return ResponseEntity.ok(new SendResponse<>(HttpStatus.OK, "Image Updated SuccessFully!", service.updateAnImage(id, file)));
    }

    /// giving user to download image stuff
    @GetMapping("/multipart/file/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws SQLException {
        Image image = service.getImageById(id);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
                .body(resource);
    }
}
