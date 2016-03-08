package by.kachanov.shop.dao;

import by.kachanov.shop.dto.Category;
import by.kachanov.shop.dto.condition.Expression;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class CategoryDaoImpl extends AbstractDao implements CategoryDao {

    @Override
    @Transactional
    public void saveCategory(Category category) {
        getCurrentSession().save(category);
    }

    @Override
    @Transactional
    public Category getCategory(BigDecimal categoryId) {
        return getCurrentSession().load(Category.class, categoryId);
    }

    @Override
    @Transactional
    public List<Category> getCategories(Expression selector) {
        return getCriteria(Category.class, selector).list();
    }

    @Override
    @Transactional
    public void deleteCategory(Category category) {
        getCurrentSession().delete(category);
    }
}