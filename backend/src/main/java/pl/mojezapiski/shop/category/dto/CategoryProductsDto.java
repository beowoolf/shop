package pl.mojezapiski.shop.category.dto;

import org.springframework.data.domain.Page;
import pl.mojezapiski.shop.common.dto.ProductListDto;
import pl.mojezapiski.shop.common.model.Category;

public record CategoryProductsDto(Category category, Page<ProductListDto> products) {
}
