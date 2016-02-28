package by.kachanov.shop.dao;

import by.kachanov.shop.dto.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class UserDaoImpl extends AbstractDao implements UserDao {

//    @Transactional
    public User getUser(BigDecimal userId) {
        return getCurrentSession().load(User.class, userId);
    }

//    @Transactional
    public List<User> getUsers() {
        return getCurrentSession().createQuery("from User").list();
    }

    @Transactional
    public void saveUser(User user) {
        getCurrentSession().save(user);
    }
}
