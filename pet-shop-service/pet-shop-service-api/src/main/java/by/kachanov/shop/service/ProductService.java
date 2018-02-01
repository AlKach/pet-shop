package by.kachanov.shop.service;

import by.kachanov.shop.dto.Product;
import by.kachanov.shop.dto.condition.Condition;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void saveProduct(Product product);

    Product getProduct(BigDecimal productId);

    List<Product> getProducts(Condition selector);

    void deleteProduct(BigDecimal productId);

}
