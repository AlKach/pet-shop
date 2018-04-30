package by.kachanov.shop.repository;

import java.math.BigInteger;

import by.kachanov.shop.dto.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, BigInteger>, JpaSpecificationExecutor<Product> {
}
