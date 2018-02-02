package by.kachanov.shop.service;

import by.kachanov.shop.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import by.kachanov.shop.repository.OrderRepository;
import by.kachanov.shop.dto.condition.Condition;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void saveOrder(Order order) {
        orderRepository.saveOrder(order);
    }

    @Override
    public Order getOrder(BigInteger orderId) {
        return orderRepository.getOrder(orderId);
    }

    @Override
    public List<Order> getOrders(Condition selector) {
        return orderRepository.getOrders(selector);
    }

    @Override
    public void deleteOrder(BigInteger orderId) {
        Order order = getOrder(orderId);
        orderRepository.deleteOrder(order);
    }
}
