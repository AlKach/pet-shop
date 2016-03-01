package by.kachanov.shop.service;

import by.kachanov.shop.dao.CategoryDao;
import by.kachanov.shop.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void saveCategory(Category category) {
        categoryDao.saveCategory(category);
    }

    @Override
    public Category getCategory(BigDecimal categoryId) {
        return categoryDao.getCategory(categoryId);
    }

    @Override
    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }

    @Override
    public void deleteCategory(BigDecimal categoryId) {
        Category category = getCategory(categoryId);
        categoryDao.deleteCategory(category);
    }
}
