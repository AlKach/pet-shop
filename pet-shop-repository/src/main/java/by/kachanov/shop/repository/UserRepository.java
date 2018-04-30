package by.kachanov.shop.repository;

import java.math.BigInteger;

import by.kachanov.shop.dto.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, BigInteger>, JpaSpecificationExecutor<User> {
}
