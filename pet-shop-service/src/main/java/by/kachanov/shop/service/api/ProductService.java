package by.kachanov.shop.service.api;

import by.kachanov.shop.dto.Product;

import java.math.BigInteger;
import java.util.List;

public interface ProductService {

    void saveProduct(Product product);

    Product getProduct(BigInteger productId);

    List<Product> getProducts(String query);

    void deleteProduct(BigInteger productId);

}
