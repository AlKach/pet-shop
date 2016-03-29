package by.kachanov.shop.dao;

import by.kachanov.shop.dto.User;
import by.kachanov.shop.dto.condition.Condition;

import java.math.BigDecimal;
import java.util.List;

public interface UserRepository {

    User getUser(BigDecimal userId);

    List<User> getUsers(Condition selector);

    void saveUser(User user);

    void deleteUser(User user);

}
