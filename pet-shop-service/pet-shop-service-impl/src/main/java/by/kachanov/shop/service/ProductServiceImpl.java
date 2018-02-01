package by.kachanov.shop.service;

import by.kachanov.shop.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import by.kachanov.shop.repository.ProductRepository;
import by.kachanov.shop.dto.condition.Condition;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        productRepository.saveProduct(product);
    }

    @Override
    public Product getProduct(BigDecimal productId) {
        return productRepository.getProduct(productId);
    }

    @Override
    public List<Product> getProducts(Condition selector) {
        return productRepository.getProducts(selector);
    }

    @Override
    public void deleteProduct(BigDecimal productId) {
        Product product = productRepository.getProduct(productId);
        productRepository.deleteProduct(product);
    }
}
