package by.kachanov.shop.dao;

import by.kachanov.shop.dto.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDao {

    void saveOrder(Order order);

    Order getOrder(BigDecimal orderId);

    List<Order> getOrders();

    void deleteOrder(Order order);

}
