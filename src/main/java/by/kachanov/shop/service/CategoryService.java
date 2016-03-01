package by.kachanov.shop.service;

import by.kachanov.shop.dto.Category;

import java.math.BigDecimal;
import java.util.List;

public interface CategoryService {

    void saveCategory(Category category);

    Category getCategory(BigDecimal categoryId);

    List<Category> getCategories();

    void deleteCategory(BigDecimal categoryId);

}
