package ru.grishchenko.controllers;

import ru.grishchenko.entity.Category;
import ru.grishchenko.repositories.CategoryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryController {

    @Inject
    private CategoryRepository categoryRepository;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategorise() {
        return categoryRepository.findAll();
    }

    public String createCategory() {
        this.category = new Category();
        return "/category_edit_form.xhtml?faces-redirect.true";
    }

    public String editCategory(Category category) {
        this.category = category;
        return "/category_edit_form.xhtml?faces-redirect-true";
    }

    public void deleteCategory(Category category) {
        categoryRepository.deleteById(category.getId());
    }

    public String saveCategory() {
        categoryRepository.saveOrUpdate(category);
        return "/categories.xhtml?faces-redirect-true";
    }
}
