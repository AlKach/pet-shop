package by.kachanov.shop.service;

import by.kachanov.shop.dto.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void saveProduct(Product product);

    Product getProduct(BigDecimal productId);

    List<Product> getProducts();

    void deleteProduct(BigDecimal productId);

}
