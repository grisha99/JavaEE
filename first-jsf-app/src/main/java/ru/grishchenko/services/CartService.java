package ru.grishchenko.services;

import dto.ProductDto;

import javax.ejb.Local;
import java.util.Map;

@Local
public interface CartService {

    Map<ProductDto, Integer> getItems();

    void addToCart(ProductDto productDto);

    void removeFromCart(ProductDto productDto);

    void clearCart();
}
