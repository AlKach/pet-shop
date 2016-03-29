package by.kachanov.shop.dao;

import by.kachanov.shop.dto.Order;
import by.kachanov.shop.dto.condition.Condition;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class OrderRepositoryImpl extends AbstractRepository implements OrderRepository {

    @Override
    @Transactional
    public void saveOrder(Order order) {
        order.getOrderPositions().stream().forEach(orderPosition -> orderPosition.setOrder(order));
        getCurrentSession().save(order);
    }

    @Override
    @Transactional
    public Order getOrder(BigDecimal orderId) {
        return getCurrentSession().load(Order.class, orderId);
    }

    @Override
    @Transactional
    public List<Order> getOrders(Condition selector) {
        return getCriteria(Order.class, selector).list();
    }

    @Override
    @Transactional
    public void deleteOrder(Order order) {
        getCurrentSession().delete(order);
    }
}
