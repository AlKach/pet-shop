package by.kachanov.shop.service;

import by.kachanov.shop.dto.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import by.kachanov.shop.repository.CategoryRepository;
import by.kachanov.shop.dto.condition.Condition;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.saveCategory(category);
    }

    @Override
    public Category getCategory(BigDecimal categoryId) {
        return categoryRepository.getCategory(categoryId);
    }

    @Override
    public List<Category> getCategories(Condition selector) {
        return categoryRepository.getCategories(selector);
    }

    @Override
    public void deleteCategory(BigDecimal categoryId) {
        Category category = getCategory(categoryId);
        categoryRepository.deleteCategory(category);
    }
}
