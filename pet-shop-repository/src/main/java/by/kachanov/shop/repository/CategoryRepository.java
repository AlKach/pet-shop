package by.kachanov.shop.repository;

import java.math.BigInteger;

import by.kachanov.shop.dto.Category;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, BigInteger>, JpaSpecificationExecutor<Category> {
}
