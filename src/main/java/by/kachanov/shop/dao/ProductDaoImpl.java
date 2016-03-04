package by.kachanov.shop.dao;

import by.kachanov.shop.dto.Product;
import by.kachanov.shop.dto.condition.Expression;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ProductDaoImpl extends AbstractDao implements ProductDao {

    @Override
    @Transactional
    public void saveProduct(Product product) {
        getCurrentSession().save(product);
    }

    @Override
    @Transactional
    public Product getProduct(BigDecimal productId) {
        return getCurrentSession().load(Product.class, productId);
    }

    @Override
    @Transactional
    public List<Product> getProducts(Expression selector) {
        return getCriteria(Product.class, selector).list();
    }

    @Override
    @Transactional
    public void deleteProduct(Product product) {
        getCurrentSession().delete(product);
    }
}
