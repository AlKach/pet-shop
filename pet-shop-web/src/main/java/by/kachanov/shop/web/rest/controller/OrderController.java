package by.kachanov.shop.web.rest.controller;

import by.kachanov.shop.dto.Order;
import by.kachanov.shop.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@Api("Orders")
@RequestMapping("/rest/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("Create order")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<BigInteger> addOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
        return new ResponseEntity<>(order.getId(), HttpStatus.CREATED);
    }

    @ApiOperation("Modify order")
    @RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
    public BigInteger modifyOrder(@PathVariable("orderId") BigInteger orderId, @RequestBody Order order) {
        Order oldOrder = orderService.getOrder(orderId);
        BeanUtils.copyProperties(order, oldOrder, "id");
        orderService.saveOrder(oldOrder);
        return oldOrder.getId();
    }

    @ApiOperation("Get order")
    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable("orderId") BigInteger orderId) {
        return orderService.getOrder(orderId);
    }

    @ApiOperation("Get orders list by query")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Order> getOrders(@RequestParam(value = "q", required = false) String query) {
        return orderService.getOrders(query);
    }

    @ApiOperation("Delete order")
    @RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrder(@PathVariable("orderId") BigInteger orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
