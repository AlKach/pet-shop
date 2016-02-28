package by.kachanov.shop.dao;

import by.kachanov.shop.dto.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserDao {

    User getUser(BigDecimal userId);

    List<User> getUsers();

    void saveUser(User user);

}
