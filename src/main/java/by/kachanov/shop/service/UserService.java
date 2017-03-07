package by.kachanov.shop.service;

import by.kachanov.shop.dto.User;
import by.kachanov.shop.dto.condition.Condition;
import org.springframework.security.access.annotation.Secured;

import java.math.BigDecimal;
import java.util.List;

import static by.kachanov.shop.dto.constants.SecurityConstants.ROLE_ADMIN;
import static by.kachanov.shop.dto.constants.SecurityConstants.ROLE_ONESELF;

public interface UserService {

    User getUser(BigDecimal userId);

    List<User> getUsers(Condition selector);

    @Secured({ ROLE_ONESELF, ROLE_ADMIN })
    void saveUser(User user);

    @Secured(ROLE_ADMIN)
    void deleteUser(BigDecimal userId);

}
