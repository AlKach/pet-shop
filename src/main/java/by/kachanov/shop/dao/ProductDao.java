package by.kachanov.shop.dao;

import by.kachanov.shop.dto.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {

    void saveProduct(Product product);

    Product getProduct(BigDecimal productId);

    List<Product> getProducts();

    void deleteProduct(Product product);

}
