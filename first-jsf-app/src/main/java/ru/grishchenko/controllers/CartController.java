package ru.grishchenko.controllers;

import ru.grishchenko.entity.Product;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Named
@SessionScoped
public class CartController implements Serializable {

    private Map<Product, Integer> items;

    @PostConstruct
    public void init() {
        items = new HashMap<Product, Integer>();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public void addToCart(Product product) {
        int count = 1;
        if (items.containsKey(product)) {
            count = items.get(product);
            count++;
        }

        items.put(product, count);
    }

    public void removeFromCart(Product product) {
        int count = items.get(product);
        count--;
        if (count <= 0) {
            items.remove(product);
        } else {
            items.put(product, count);
        }
    }

    public void clearCart() {
        items.clear();
    }
}
