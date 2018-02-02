package by.kachanov.shop.repository;

import by.kachanov.shop.dto.Product;
import by.kachanov.shop.dto.condition.Condition;

import java.math.BigInteger;
import java.util.List;

public interface ProductRepository {

    void saveProduct(Product product);

    Product getProduct(BigInteger productId);

    List<Product> getProducts(Condition selector);

    void deleteProduct(Product product);

}
