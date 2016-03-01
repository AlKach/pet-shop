package by.kachanov.shop.service;

import by.kachanov.shop.dao.UserDao;
import by.kachanov.shop.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(BigDecimal userId) {
        return userDao.getUser(userId);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void deleteUser(BigDecimal userId) {
        User user = getUser(userId);
        userDao.deleteUser(user);
    }
}
