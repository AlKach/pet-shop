package by.kachanov.shop.dao;

import by.kachanov.shop.dto.Category;

import java.math.BigDecimal;
import java.util.List;

public interface CategoryDao {

    void saveCategory(Category category);

    Category getCategory(BigDecimal categoryId);

    List<Category> getCategories();

    void deleteCategory(Category category);

}
