package by.kachanov.shop.repository;

import by.kachanov.shop.dto.Order;
import by.kachanov.shop.dto.condition.Condition;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository {

    void saveOrder(Order order);

    Order getOrder(BigDecimal orderId);

    List<Order> getOrders(Condition selector);

    void deleteOrder(Order order);

}
