package com.suraj.NeoShop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Image {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileType;
    @Getter
    private String fileName;

    @Lob
    private Blob image;

    @Getter
    private String url;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
