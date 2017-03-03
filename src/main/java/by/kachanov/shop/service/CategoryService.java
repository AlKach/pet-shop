package by.kachanov.shop.service;

import by.kachanov.shop.dto.Category;
import by.kachanov.shop.dto.condition.Condition;
import org.springframework.security.access.annotation.Secured;

import java.math.BigDecimal;
import java.util.List;

public interface CategoryService {

    @Secured("ROLE_ADMIN")
    void saveCategory(Category category);

    Category getCategory(BigDecimal categoryId);

    List<Category> getCategories(Condition selector);

    @Secured("ROLE_ADMIN")
    void deleteCategory(BigDecimal categoryId);

}
