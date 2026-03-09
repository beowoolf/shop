package pl.mojezapiski.shop.homepage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mojezapiski.shop.homepage.controller.dto.HomePageDto;
import pl.mojezapiski.shop.homepage.service.HomePageService;

@RestController
@RequiredArgsConstructor
public class HomePageController {
    private final HomePageService homePageService;

    @Cacheable("homePage")
    @GetMapping("/homePage")
    public HomePageDto getHomePage() {
        return new HomePageDto(homePageService.getSaleProducts());
    }
}
