package pl.mojezapiski.shop.cart.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mojezapiski.shop.cart.model.dto.CartProductDto;
import pl.mojezapiski.shop.common.model.Cart;
import pl.mojezapiski.shop.common.model.CartItem;
import pl.mojezapiski.shop.common.model.Product;
import pl.mojezapiski.shop.common.repository.CartRepository;
import pl.mojezapiski.shop.common.repository.ProductRepository;

import java.util.List;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Cart getCart(Long id) {
        return cartRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Cart addProductToCart(Long id, CartProductDto cartProductDto) {
        Cart cart = getInitializedCart(id);
        cart.addProduct(CartItem.builder()
                .quantity(cartProductDto.quantity())
                .product(getProduct(cartProductDto.productId()))
                .cartId(cart.getId())
                .build());
        return cart;
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow();
    }

    private Cart getInitializedCart(Long id) {
        if (id == null || id <= 0) {
            return saveNewCart();
        }
        return cartRepository.findById(id).orElseGet(this::saveNewCart);
    }

    private Cart saveNewCart() {
        return cartRepository.save(Cart.builder().created(now()).build());
    }

    @Transactional
    public Cart updateCart(Long id, List<CartProductDto> cartProductDtos) {
        Cart cart = cartRepository.findById(id).orElseThrow();
        cart.getItems().forEach(cartItem -> {
            cartProductDtos.stream()
                    .filter(cartProductDto -> cartItem.getProduct().getId().equals(cartProductDto.productId()))
                    .findFirst()
                    .ifPresent(cartProductDto -> cartItem.setQuantity(cartProductDto.quantity()));
        });
        return cart;
    }
}
