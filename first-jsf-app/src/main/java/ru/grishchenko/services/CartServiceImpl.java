package ru.grishchenko.services;

import dto.ProductDto;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import java.util.HashMap;
import java.util.Map;

@Stateful
public class CartServiceImpl implements CartService{

    private Map<ProductDto, Integer> items;

    @PostConstruct
    public void init() {
        items = new HashMap<>();
    }

    public Map<ProductDto, Integer> getItems() {
        return items;
    }

    @Override
    public void addToCart(ProductDto productDto) {
        int count = 1;
        if (items.containsKey(productDto)) {
            count = items.get(productDto);
            count++;
        }

        items.put(productDto, count);
    }

    @Override
    public void removeFromCart(ProductDto productDto) {
        int count = items.get(productDto);
        count--;
        if (count <= 0) {
            items.remove(productDto);
        } else {
            items.put(productDto, count);
        }
    }

    @Override
    public void clearCart() {
        items.clear();
    }
}
