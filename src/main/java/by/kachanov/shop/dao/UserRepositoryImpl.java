package by.kachanov.shop.dao;

import by.kachanov.shop.dto.User;
import by.kachanov.shop.dto.condition.Condition;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class UserRepositoryImpl extends AbstractRepository implements UserRepository {

    @Override
    @Transactional
    public User getUser(BigDecimal userId) {
        return getCurrentSession().load(User.class, userId);
    }

    @Override
    @Transactional
    public List<User> getUsers(Condition selector) {
        return getCriteria(User.class, selector).list();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        getCurrentSession().save(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        getCurrentSession().delete(user);
    }
}
