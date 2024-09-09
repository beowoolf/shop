package pl.mojezapiski.shop.admin.category.controller.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;

@Getter
public class AdminCategoryDto {
    @NotBlank
    @Length(min = 4)
    private String name;
    private String description;
    @NotBlank
    @Length(min = 4)
    private String slug;
}
