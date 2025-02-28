package com.suraj.NeoShop.serviceImpl.image;

import com.suraj.NeoShop.dto.ImageDto;
import com.suraj.NeoShop.exception.ResourceNotFoundException;
import com.suraj.NeoShop.mapper.Mapper;
import com.suraj.NeoShop.model.Image;
import com.suraj.NeoShop.model.Product;
import com.suraj.NeoShop.repository.image.ImageRepository;
import com.suraj.NeoShop.service.image.ImageService;
import com.suraj.NeoShop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        Image image = getImageById(id);
        imageRepo.delete(image);
    }

    /// upload image
    @Override
    public List<ImageDto> uploadImageForProduct(Long prodId, List<MultipartFile> files) {
        //first find a product
        Product product = productService.getProductById(prodId);

        List<ImageDto> savedImageDto = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                // getting and setting image params
                Image image = new Image();

                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setProduct(product);
                image.setImage(new SerialBlob((file.getBytes()))); /// storing in bytes


                Image savedimage = imageRepo.save(image);///do-not forgot to save

                //for download url stuff
                String basicUrl = "/api/v1/neoshop/image/multipart/file/";

                /// update an url
                savedimage.setUrl(basicUrl + savedimage.getId());

                Image finalSavedimage = imageRepo.save(savedimage);

                /// now convert image to dto
                ImageDto imageDto = Mapper.convertToImageDto(finalSavedimage);

                /// and append this to list of image dto
                savedImageDto.add(imageDto);
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageDto;
    }


    /// update and image
    @Override
    public ImageDto updateAnImage(Long id, MultipartFile file) {
        Image image = getImageById(id);
        try {
            image.setFileName(file.getName());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepo.save(image);///do not forget to save
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return Mapper.convertToImageDto(image);
    }

}
