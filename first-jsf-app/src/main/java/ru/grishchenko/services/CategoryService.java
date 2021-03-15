package ru.grishchenko.services;

import dto.CategoryDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CategoryService {

    List<CategoryDto> findAll();

    CategoryDto findById(Long id);

    CategoryDto getReference(Long id);

    Long getCategoryCount();

    void saveOrUpdate(CategoryDto categoryDto);

    void deleteById(Long id);
}
