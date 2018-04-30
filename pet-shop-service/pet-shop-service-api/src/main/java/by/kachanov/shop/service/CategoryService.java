package by.kachanov.shop.service;

import by.kachanov.shop.dto.Category;

import java.math.BigInteger;
import java.util.List;

public interface CategoryService {

    void saveCategory(Category category);

    Category getCategory(BigInteger categoryId);

    List<Category> getCategories(String query);

    void deleteCategory(BigInteger categoryId);

}
