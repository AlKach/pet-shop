package by.kachanov.shop.dao;

import by.kachanov.shop.dto.Category;
import by.kachanov.shop.dto.condition.Condition;

import java.math.BigDecimal;
import java.util.List;

public interface CategoryRepository {

    void saveCategory(Category category);

    Category getCategory(BigDecimal categoryId);

    List<Category> getCategories(Condition selector);

    void deleteCategory(Category category);

}
