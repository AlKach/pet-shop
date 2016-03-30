package by.kachanov.shop.service;

import by.kachanov.shop.dto.Order;
import by.kachanov.shop.dto.condition.Condition;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    void saveOrder(Order order);

    Order getOrder(BigDecimal orderId);

    List<Order> getOrders(Condition selector);

    void deleteOrder(BigDecimal orderId);

}
