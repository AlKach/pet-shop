package by.kachanov.shop.service;

import by.kachanov.shop.dao.UserDao;
import by.kachanov.shop.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User getUser(BigDecimal userId) {
        return userDao.getUser(userId);
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }
}
