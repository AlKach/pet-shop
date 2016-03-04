package by.kachanov.shop.service;

import by.kachanov.shop.dto.User;
import by.kachanov.shop.dto.condition.Expression;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    User getUser(BigDecimal userId);

    List<User> getUsers(Expression selector);

    void saveUser(User user);

    void deleteUser(BigDecimal userId);

}
