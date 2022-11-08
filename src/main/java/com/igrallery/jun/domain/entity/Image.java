package com.igrallery.jun.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Image {

    protected Image() {}
    public Image(Gallery gallery, ItemType itemType, Integer priority, String originalName, String savedName, String path) {
        this.itemType = itemType;
        this.gallery = gallery;
        this.priority = priority;
        this.originalName = originalName;
        this.savedName = savedName;
        this.path = path;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "GID") private Gallery gallery;
    @Column(name = "PRIORITY") private Integer priority;

    @Enumerated(EnumType.STRING) ItemType itemType;
    @OneToOne(mappedBy = "image") private Metadata metadata;

    private String originalName;
    private String savedName;
    private String path;
}
