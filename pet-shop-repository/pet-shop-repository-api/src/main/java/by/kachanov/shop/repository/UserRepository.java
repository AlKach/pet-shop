package by.kachanov.shop.repository;

import by.kachanov.shop.dto.User;
import by.kachanov.shop.dto.condition.Condition;

import java.math.BigInteger;
import java.util.List;

public interface UserRepository {

    User getUser(BigInteger userId);

    List<User> getUsers(Condition selector);

    void saveUser(User user);

    void deleteUser(User user);

}
