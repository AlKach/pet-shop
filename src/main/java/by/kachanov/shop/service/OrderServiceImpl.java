package by.kachanov.shop.service;

import by.kachanov.shop.dto.Order;
import by.kachanov.shop.dto.condition.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import by.kachanov.shop.dao.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void saveOrder(Order order) {
        orderRepository.saveOrder(order);
    }

    @Override
    public Order getOrder(BigDecimal orderId) {
        return orderRepository.getOrder(orderId);
    }

    @Override
    public List<Order> getOrders(Expression selector) {
        return orderRepository.getOrders(selector);
    }

    @Override
    public void deleteOrder(BigDecimal orderId) {
        Order order = getOrder(orderId);
        orderRepository.deleteOrder(order);
    }
}
