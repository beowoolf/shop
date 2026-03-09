package pl.mojezapiski.shop.homepage.controller.dto;

import pl.mojezapiski.shop.common.model.Product;

import java.util.List;

public record HomePageDto(List<Product> saleProducts) {
}
