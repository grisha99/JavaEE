package ru.grishchenko.services;

import dto.CategoryDto;
import ru.grishchenko.entity.Category;
import ru.grishchenko.repositories.CategoryRepository;
import ru.grishchenko.rest.CategoryServiceRest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CategoryServiceImpl implements CategoryService, CategoryServiceRest {

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
//        return categoryRepository.findAll().stream().map(CategoryDto::new).collect(Collectors.toList());
        return categoryRepository.findAll().stream().map(c -> new CategoryDto(
                    c.getId(),
                    c.getTitle(),
                    c.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id);
        if (category != null) {
            return new CategoryDto(category.getId(), category.getTitle(), category.getDescription());
        }
        return null;
    }

    @Override
    public CategoryDto getReference(Long id) {
        Category category = categoryRepository.getReference(id);
        if (category != null) {
            return new CategoryDto(category.getId(), category.getTitle(), category.getDescription());
        }

        return null;
    }

    @Override
    public Long getCategoryCount() {
        return categoryRepository.getCategoryCount();
    }

    @Override
    public void insert(CategoryDto categoryDto) {
        if (categoryDto.getId() != null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(categoryDto);
    }

    @Override
    public void update(CategoryDto categoryDto) {
        if (categoryDto.getId() == null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(categoryDto);
    }

    @Override
    @TransactionAttribute
    public void saveOrUpdate(CategoryDto categoryDto) {
        categoryRepository.saveOrUpdate(new Category(categoryDto));
    }

    @Override
    @TransactionAttribute
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
