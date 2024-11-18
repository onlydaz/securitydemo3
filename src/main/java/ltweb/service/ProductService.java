package ltweb.service;

import ltweb.model.Product;
import java.util.List;

public interface ProductService {
    void delete(Long id);
    Product get(Long id);
    Product save(Product product);
    List<Product> listAll();
}
