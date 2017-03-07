package by.kachanov.shop.service;

import by.kachanov.shop.dto.Order;
import by.kachanov.shop.dto.condition.Condition;
import org.springframework.security.access.annotation.Secured;

import java.math.BigDecimal;
import java.util.List;

import static by.kachanov.shop.dto.constants.SecurityConstants.ROLE_OWNER;
import static by.kachanov.shop.dto.constants.SecurityConstants.ROLE_USER;

public interface OrderService {

    @Secured(ROLE_USER)
    void saveOrder(Order order);

    Order getOrder(BigDecimal orderId);

    List<Order> getOrders(Condition selector);

    void deleteOrder(BigDecimal orderId);

}
