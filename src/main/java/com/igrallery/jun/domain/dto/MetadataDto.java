package com.igrallery.jun.domain.dto;

import lombok.Data;

@Data
public class MetadataDto {
    private String date;
    private String name;
    private double lat; //위도
    private double lon; //경도

}
