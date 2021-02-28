package ru.grishchenko.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@NamedQueries({
        @NamedQuery(name = "Category.findAll", query = "from Category"),
        @NamedQuery(name = "getCategoryCount", query = "select count(*) from Category"),
        @NamedQuery(name = "Category.deleteById", query = "delete from Category c where c.id = :id")
})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String Description;

    @OneToMany(mappedBy = "category")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Product> productList;

    public Category() {
    }

    public Category(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        Description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
