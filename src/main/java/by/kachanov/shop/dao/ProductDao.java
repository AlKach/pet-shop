package by.kachanov.shop.dao;

import by.kachanov.shop.dto.Product;
import by.kachanov.shop.dto.condition.Expression;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {

    void saveProduct(Product product);

    Product getProduct(BigDecimal productId);

    List<Product> getProducts(Expression selector);

    void deleteProduct(Product product);

}
