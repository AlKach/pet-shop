package by.kachanov.shop.service;

import by.kachanov.shop.dto.User;
import by.kachanov.shop.dto.condition.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import by.kachanov.shop.dao.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(BigDecimal userId) {
        return userRepository.getUser(userId);
    }

    @Override
    public List<User> getUsers(Expression selector) {
        return userRepository.getUsers(selector);
    }

    @Override
    public void saveUser(User user) {
        userRepository.saveUser(user);
    }

    @Override
    public void deleteUser(BigDecimal userId) {
        User user = getUser(userId);
        userRepository.deleteUser(user);
    }
}
