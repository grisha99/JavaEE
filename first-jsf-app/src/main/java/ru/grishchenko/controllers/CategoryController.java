package ru.grishchenko.controllers;

import dto.CategoryDto;
import ru.grishchenko.entity.Category;
import ru.grishchenko.services.CategoryService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryController {

    @EJB
    private CategoryService categoryService;

    private CategoryDto categoryDto;

    private List<CategoryDto> categories;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        categories = categoryService.findAll();
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public String createCategory() {
        this.categoryDto = new CategoryDto();
        return "/category_edit_form.xhtml?faces-redirect=true";
    }

    public String editCategory(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
        return "/category_edit_form.xhtml?faces-redirect=true";
    }

    public void deleteCategory(Category category) {
        categoryService.deleteById(category.getId());
    }

    public String saveCategory() {
        categoryService.saveOrUpdate(categoryDto);
        return "/categories.xhtml?faces-redirect=true";
    }
}
