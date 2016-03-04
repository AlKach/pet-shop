package by.kachanov.shop.web.rest.controller;

import by.kachanov.shop.dto.User;
import by.kachanov.shop.service.UserService;
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
@Api("Users")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("Create user")
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public BigDecimal createUser(@RequestBody User user) {
        userService.saveUser(user);
        return user.getId();
    }

    @ApiOperation("Modify user")
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    @ResponseBody
    public BigDecimal modifyUser(@PathVariable("userId") BigDecimal userId, @RequestBody User user) {
        User oldUser = userService.getUser(userId);
        BeanUtils.copyProperties(user, oldUser, "id");
        userService.saveUser(oldUser);
        return user.getId();
    }

    @ApiOperation("Get user")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable("userId") BigDecimal userId) {
        return userService.getUser(userId);
    }

    @ApiOperation("Get users list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @ApiOperation("Delete user")
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteUser(@PathVariable("userId") BigDecimal userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
