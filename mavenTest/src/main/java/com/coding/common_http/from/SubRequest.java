package com.coding.common_http.from;

import lombok.Data;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Data
public class SubRequest {
    @NotBlank
    private String entity;

    @Valid
    @NotNull
    private SubSubEntity subSubEntity;
}
