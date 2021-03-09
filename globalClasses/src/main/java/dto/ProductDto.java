package dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ProductDto implements Serializable {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Long categoryId;

    private String categoryName;

    public ProductDto() {
    }

//    public ProductDto(Product product) {
//        this.id = product.getId();
//        this.name = product.getName();
//        this.description = product.getDescription();
//        this.price = product.getPrice();
//        Category category = product.getCategory();
//        categoryId = category != null ? category.getId() : null;
//        categoryName = category != null ? category.getTitle() : null;
//    }

    public ProductDto(Long id, String name, String description, BigDecimal price, CategoryDto categoryDto) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        categoryId = categoryDto != null ? categoryDto.getId() : null;
        categoryName = categoryDto != null ? categoryDto.getTitle() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price) &&
                Objects.equals(categoryId, that.categoryId) &&
                Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, categoryId, categoryName);
    }
}
