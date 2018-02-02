package by.kachanov.shop.repository;

import by.kachanov.shop.dto.Category;
import by.kachanov.shop.dto.condition.Condition;

import java.math.BigInteger;
import java.util.List;

public interface CategoryRepository {

    void saveCategory(Category category);

    Category getCategory(BigInteger categoryId);

    List<Category> getCategories(Condition selector);

    void deleteCategory(Category category);

}
