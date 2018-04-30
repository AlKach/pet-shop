package by.kachanov.shop.service;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;

import by.kachanov.shop.dto.Category;
import by.kachanov.shop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseService<Category> implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category getCategory(BigInteger categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Category> getCategories(String query) {
        return categoryRepository.findAll(buildSpecification(query));
    }

    @Override
    public void deleteCategory(BigInteger categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
