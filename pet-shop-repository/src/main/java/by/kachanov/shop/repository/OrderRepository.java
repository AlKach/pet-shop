package by.kachanov.shop.repository;

import java.math.BigInteger;

import by.kachanov.shop.dto.Order;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, BigInteger>, JpaSpecificationExecutor<Order> {
}
