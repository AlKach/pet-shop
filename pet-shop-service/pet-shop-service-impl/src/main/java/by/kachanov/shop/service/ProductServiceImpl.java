package by.kachanov.shop.service;

import javax.persistence.EntityNotFoundException;

import by.kachanov.shop.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import by.kachanov.shop.repository.ProductRepository;

@Service
public class ProductServiceImpl extends BaseService<Product> implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product getProduct(BigInteger productId) {
        return productRepository.findById(productId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Product> getProducts(String query) {
        return productRepository.findAll(buildSpecification(query));
    }

    @Override
    public void deleteProduct(BigInteger productId) {
        productRepository.deleteById(productId);
    }
}
