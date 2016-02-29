package by.kachanov.shop.service;

import by.kachanov.shop.dto.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    User getUser(BigDecimal userId);

    List<User> getUsers();

    void saveUser(User user);

    void deleteUser(User user);

}
