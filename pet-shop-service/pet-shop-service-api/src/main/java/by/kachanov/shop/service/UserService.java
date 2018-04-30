package by.kachanov.shop.service;

import by.kachanov.shop.dto.User;

import java.math.BigInteger;
import java.util.List;

public interface UserService {

    User getUser(BigInteger userId);

    List<User> getUsers(String query);

    void saveUser(User user);

    void deleteUser(BigInteger userId);

}
