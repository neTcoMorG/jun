package com.igrallery.jun.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Gallery {

    public Gallery(String name, String sub) {
        this.name = name;
        this.sub = sub;
    }
    protected  Gallery() {}

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @Column(name = "NAME") private String name;
    @Column(name = "SUB") private String sub;
    @Column(name = "CREATED_DATE") @CreatedDate private LocalDateTime createdDate;

    @Column(name = "THUMBNAIL") private String thumbnail;

    @OneToMany(mappedBy = "gallery") private List<Image> images;
}
