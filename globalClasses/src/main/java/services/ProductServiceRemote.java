package services;

import dto.ProductDto;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ProductServiceRemote {

    List<ProductDto> findAll();

    Long getProductsCount();

    ProductDto findById(Long id);

}
