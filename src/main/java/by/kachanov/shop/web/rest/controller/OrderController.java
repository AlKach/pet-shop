package by.kachanov.shop.web.rest.controller;

import by.kachanov.shop.dto.Order;
import by.kachanov.shop.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@Api("Orders")
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("Create order")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public BigDecimal addOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
        return order.getId();
    }

    @ApiOperation("Modify order")
    @RequestMapping(value = "/{orderId}", method = RequestMethod.PUT)
    @ResponseBody
    public BigDecimal modifyOrder(@PathVariable("orderId") BigDecimal orderId, @RequestBody Order order) {
        Order oldOrder = orderService.getOrder(orderId);
        BeanUtils.copyProperties(order, oldOrder, "id");
        orderService.saveOrder(oldOrder);
        return oldOrder.getId();
    }

    @ApiOperation("Get order")
    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    @ResponseBody
    public Order getOrder(@PathVariable("orderId") BigDecimal orderId) {
        return orderService.getOrder(orderId);
    }

    @ApiOperation("Get orders list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @ApiOperation("Delete order")
    @RequestMapping(value = "/{orderId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteOrder(@PathVariable("orderId") BigDecimal orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
