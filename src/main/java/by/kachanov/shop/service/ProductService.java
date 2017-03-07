package by.kachanov.shop.service;

import by.kachanov.shop.dto.Product;
import by.kachanov.shop.dto.condition.Condition;
import org.springframework.security.access.annotation.Secured;

import java.math.BigDecimal;
import java.util.List;

import static by.kachanov.shop.dto.constants.SecurityConstants.ROLE_ADMIN;

public interface ProductService {

    @Secured(ROLE_ADMIN)
    void saveProduct(Product product);

    Product getProduct(BigDecimal productId);

    List<Product> getProducts(Condition selector);

    @Secured(ROLE_ADMIN)
    void deleteProduct(BigDecimal productId);

}
