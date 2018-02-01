package by.kachanov.shop.repository;

import by.kachanov.shop.dto.Category;
import by.kachanov.shop.dto.condition.Condition;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class CategoryRepositoryImpl extends AbstractRepository implements CategoryRepository {

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
    @SuppressWarnings("unchecked")
    public List<Category> getCategories(Condition selector) {
        return getCriteria(Category.class, selector).list();
    }

    @Override
    @Transactional
    public void deleteCategory(Category category) {
        getCurrentSession().delete(category);
    }
}
