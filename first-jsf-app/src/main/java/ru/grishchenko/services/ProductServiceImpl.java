package ru.grishchenko.services;

import dto.CategoryDto;
import dto.ProductDto;
import ru.grishchenko.entity.Category;
import ru.grishchenko.entity.Product;
import ru.grishchenko.repositories.ProductRepository;
import ru.grishchenko.rest.ProductServiceRest;
import services.ProductServiceRemote;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductServiceImpl implements ProductService, ProductServiceRemote, ProductServiceRest {

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
    public List<ProductDto> findByName(String name) {
        return productRepository.findByName(name).stream().map(this::createProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findByCategoryId(Long id) {
        return productRepository.findProductByCategoryId(id).stream().map(this::createProductDto).collect(Collectors.toList());
    }

    @Override
    public void insert(ProductDto productDto) {
        if (productDto.getId() != null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(productDto);
    }

    @Override
    public void update(ProductDto productDto) {
        if (productDto.getId() == null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(productDto);
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

    public ProductDto createProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        Category category = product.getCategory();
        productDto.setCategoryId(category != null ? category.getId() : null);
        productDto.setCategoryName(category != null ? category.getTitle() : null);
        return productDto;
    }
}
