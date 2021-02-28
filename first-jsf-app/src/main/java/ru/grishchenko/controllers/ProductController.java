package ru.grishchenko.controllers;

import ru.grishchenko.entity.Product;
import ru.grishchenko.repositories.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @Inject
    private ProductRepository productRepository;

    private Product product;

    private List<Product> products;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        products = productRepository.findAll();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new Product();
        return "/product_edit_form.xhtml?faces-redirect=true";
    }

    public List<Product> getProducts() {
        return products;
    }

    public String editProduct(Product product) {
        this.product = product;
        return "/product_edit_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product) {
        productRepository.deleteById(product.getId());
    }

    public String saveProduct() {
        productRepository.saveOrUpdate(product);
        return "/products.xhtml?faces-redirect=true";
    }

}
