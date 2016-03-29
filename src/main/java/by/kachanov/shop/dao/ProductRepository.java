package by.kachanov.shop.dao;

import by.kachanov.shop.dto.Product;
import by.kachanov.shop.dto.condition.Condition;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository {

    void saveProduct(Product product);

    Product getProduct(BigDecimal productId);

    List<Product> getProducts(Condition selector);

    void deleteProduct(Product product);

}
