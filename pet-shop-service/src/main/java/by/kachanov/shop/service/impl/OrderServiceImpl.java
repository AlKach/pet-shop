package by.kachanov.shop.service.impl;

import javax.persistence.EntityNotFoundException;

import by.kachanov.shop.dto.Order;
import by.kachanov.shop.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import by.kachanov.shop.repository.OrderRepository;

@Service
public class OrderServiceImpl extends BaseService<Order> implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order getOrder(BigInteger orderId) {
        return orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Order> getOrders(String query) {
        return orderRepository.findAll(buildSpecification(query));
    }

    @Override
    public void deleteOrder(BigInteger orderId) {
        orderRepository.deleteById(orderId);
    }
}
