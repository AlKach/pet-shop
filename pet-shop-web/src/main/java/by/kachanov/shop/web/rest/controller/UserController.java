package by.kachanov.shop.web.rest.controller;

import by.kachanov.shop.dto.User;
import by.kachanov.shop.service.api.UserService;
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
@Api("Users")
@RequestMapping("/rest/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("Create user")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<BigInteger> createUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(user.getId(), HttpStatus.CREATED);
    }

    @ApiOperation("Modify user")
    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public BigInteger modifyUser(@PathVariable("userId") BigInteger userId, @RequestBody User user) {
        User oldUser = userService.getUser(userId);
        BeanUtils.copyProperties(user, oldUser, "id");
        userService.saveUser(oldUser);
        return user.getId();
    }

    @ApiOperation("Get user")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable("userId") BigInteger userId) {
        return userService.getUser(userId);
    }

    @ApiOperation("Get users list by query")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getUsers(@RequestParam(value = "q", required = false) String query) {
        return userService.getUsers(query);
    }

    @ApiOperation("Delete user")
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("userId") BigInteger userId) {
        userService.deleteUser(userId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
