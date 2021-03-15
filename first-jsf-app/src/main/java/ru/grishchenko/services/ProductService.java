package ru.grishchenko.services;

import dto.ProductDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {

    List<ProductDto> findAll();

    Long getProductsCount();

    ProductDto findById(Long id);

    void saveOrUpdate(ProductDto productDto);

    void deleteById(Long id);
}
