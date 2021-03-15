package ru.grishchenko.controllers;

import dto.CategoryDto;
import dto.ProductDto;
import ru.grishchenko.entity.Product;
import ru.grishchenko.services.CategoryService;
import ru.grishchenko.services.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

//    @Inject
//    private ProductRepository productRepository;

    @EJB
    private ProductService productService;

    @EJB
    private CategoryService categoryService;

    private ProductDto productDto;

    private List<ProductDto> products;

    private List<CategoryDto> categories;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        products = productService.findAll();
        categories = categoryService.findAll();
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public String createProduct() {
        this.productDto = new ProductDto();
        return "/product_edit_form.xhtml?faces-redirect=true";
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public String editProduct(ProductDto productDto) {
        this.productDto = productDto;
        return "/product_edit_form.xhtml?faces-redirect=true";
    }

    public void deleteProduct(ProductDto productDto) {
        productService.deleteById(productDto.getId());
    }

    public String saveProduct() {
        productService.saveOrUpdate(productDto);
        return "/products.xhtml?faces-redirect=true";
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }
}
