package by.kachanov.shop.service.impl;

import javax.persistence.EntityNotFoundException;

import by.kachanov.shop.dto.User;
import by.kachanov.shop.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import by.kachanov.shop.repository.UserRepository;

@Service
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(BigInteger userId) {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<User> getUsers(String query) {
        return userRepository.findAll(buildSpecification(query));
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(BigInteger userId) {
        userRepository.deleteById(userId);
    }
}
