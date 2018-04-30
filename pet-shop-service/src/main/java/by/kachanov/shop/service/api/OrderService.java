package by.kachanov.shop.service.api;

import by.kachanov.shop.dto.Order;

import java.math.BigInteger;
import java.util.List;

public interface OrderService {

    void saveOrder(Order order);

    Order getOrder(BigInteger orderId);

    List<Order> getOrders(String query);

    void deleteOrder(BigInteger orderId);

}
