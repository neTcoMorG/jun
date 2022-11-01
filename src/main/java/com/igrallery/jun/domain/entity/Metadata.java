package com.igrallery.jun.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Metadata {

    public Metadata() {}
    public Metadata(Image image, String name, String createDate, Double lat, Double lon, String device) {
        this.image = image;
        this.name = name;
        this.date = createDate;
        this.lat = lat;
        this.lon = lon;
        this.device = device;
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String name;
    private String date;
    private Double lat;
    private Double lon;
    private String device;

    @OneToOne @JoinColumn(name = "IMAGE_ID") private Image image;
}
