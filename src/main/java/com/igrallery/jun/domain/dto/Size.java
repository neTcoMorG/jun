package com.igrallery.jun.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Size {
    @NotBlank private String type;
}
