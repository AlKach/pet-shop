package by.kachanov.shop.service;

import by.kachanov.shop.dao.ProductDao;
import by.kachanov.shop.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }

    @Override
    public Product getProduct(BigDecimal productId) {
        return productDao.getProduct(productId);
    }

    @Override
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    @Override
    public void deleteProduct(BigDecimal productId) {
        Product product = productDao.getProduct(productId);
        productDao.deleteProduct(product);
    }
}
