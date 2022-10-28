package com.igrallery.jun.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Image {

    protected Image() {}

    public Image(Gallery gallery, ItemType itemType, String originalName, String savedName, String path) {
        this.itemType = itemType;
        this.gallery = gallery;
        this.originalName = originalName;
        this.savedName = savedName;
        this.path = path;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "GID") private Gallery gallery;

    @Enumerated(EnumType.STRING) ItemType itemType;

    private String originalName;
    private String savedName;
    private String path;
}
