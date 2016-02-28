package by.kachanov.shop.web;

import by.kachanov.shop.dto.User;
import by.kachanov.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/create")
    @ResponseBody
    public BigDecimal createUser() {
        User user = new User();
        user.setName("Test");
        user.setLogin("test");
        user.setPassword("text");

        userService.saveUser(user);

        return user.getId();
    }

    @RequestMapping("/{userId}")
    @ResponseBody
    public User getUser(@PathVariable("userId") BigDecimal userId) {
        return userService.getUser(userId);
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<User> getUsers() {
        return userService.getUsers();
    }

}
