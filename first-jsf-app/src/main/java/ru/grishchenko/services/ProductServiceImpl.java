package ru.grishchenko.services;

import dto.CategoryDto;
import dto.ProductDto;
import ru.grishchenko.entity.Category;
import ru.grishchenko.entity.Product;
import ru.grishchenko.repositories.ProductRepository;
import services.ProductServiceRemote;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductServiceImpl implements ProductService, ProductServiceRemote {

    @EJB
    private ProductRepository productRepository;

//    @EJB
//    private CategoryRepository categoryRepository;

    @EJB
    private CategoryService categoryService;

    @Override
    public List<ProductDto> findAll() {
//        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
        return productRepository.findAll().stream().map(p ->
                new ProductDto(p.getId(),
                                p.getName(),
                                p.getDescription(),
                                p.getPrice(),
                                new CategoryDto(p.getCategory().getId(),
                                        p.getCategory().getTitle(),
                                        p.getCategory().getDescription())))
                .collect(Collectors.toList());
    }

    @Override
    public Long getProductsCount() {
        return productRepository.getProductsCount();
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);
        if (product != null) {
//            return new ProductDto(product);
            return new ProductDto(product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice(),
                    new CategoryDto(product.getCategory().getId(),
                            product.getCategory().getTitle(),
                            product.getCategory().getDescription()));
        }
        return null;
    }

    @Override
    @TransactionAttribute
    public void saveOrUpdate(ProductDto productDto) {
        CategoryDto categoryDto = null;
        if (productDto.getCategoryId() != null) {
            categoryDto = categoryService.getReference(productDto.getCategoryId());
        }
        productRepository.saveOrUpdate(new Product(productDto, new Category(categoryDto)));
    }

    @Override
    @TransactionAttribute
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
