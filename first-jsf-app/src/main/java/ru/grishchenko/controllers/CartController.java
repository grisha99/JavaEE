package ru.grishchenko.controllers;

import dto.ProductDto;
import ru.grishchenko.services.CartService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;

@Named
@SessionScoped
public class CartController implements Serializable {

//    private Map<Product, Integer> items;

//    @PostConstruct
//    public void init() {
//        items = new HashMap<Product, Integer>();
//    }

//    public Map<Product, Integer> getItems() {
//        return items;
//    }

    @EJB
    private CartService cartService;

    public Map<ProductDto, Integer> getItems() {
        return cartService.getItems();
    }

    public void addToCart(ProductDto productDto) {
        cartService.addToCart(productDto);
    }

    public void removeFromCart(ProductDto productDto) {
        cartService.removeFromCart(productDto);
    }

    public void clearCart() {
        cartService.clearCart();
    }
}
