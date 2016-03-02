package by.kachanov.shop.service;

import by.kachanov.shop.dao.OrderDao;
import by.kachanov.shop.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void saveOrder(Order order) {
        orderDao.saveOrder(order);
    }

    @Override
    public Order getOrder(BigDecimal orderId) {
        return orderDao.getOrder(orderId);
    }

    @Override
    public List<Order> getOrders() {
        return orderDao.getOrders();
    }

    @Override
    public void deleteOrder(BigDecimal orderId) {
        Order order = getOrder(orderId);
        orderDao.deleteOrder(order);
    }
}
