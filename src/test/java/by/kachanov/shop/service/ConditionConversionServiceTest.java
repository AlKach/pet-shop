package by.kachanov.shop.service;

import by.kachanov.shop.SpringTest;
import by.kachanov.shop.dto.User;
import by.kachanov.shop.dto.condition.*;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static by.kachanov.shop.TestConstants.*;
import static org.junit.Assert.*;

public class ConditionConversionServiceTest extends SpringTest {

    @Autowired
    private UserService userService;

    private List<User> users = new ArrayList<>();

    private BigDecimal firstUserId = null;

    private BigDecimal lastUserId = null;

    @Before
    public void setUp() throws Exception {
        users.add(createUser(TEST_NAME_1, TEST_LOGIN_1, TEST_PASSWORD_1));
        users.add(createUser(TEST_NAME_2, TEST_LOGIN_2, TEST_PASSWORD_2));
        users.add(createUser(TEST_NAME_3, TEST_LOGIN_3, TEST_PASSWORD_3));
        users.add(createUser(TEST_NAME_4, TEST_LOGIN_4, TEST_PASSWORD_4));
        users.add(createUser(TEST_NAME_5, TEST_LOGIN_5, TEST_PASSWORD_5));
        users.add(createUser(TEST_NAME_6, TEST_LOGIN_6, TEST_PASSWORD_6));
        users.add(createUser(TEST_NAME_7, TEST_LOGIN_7, TEST_PASSWORD_7));
        users.add(createUser(TEST_NAME_8, TEST_LOGIN_8, TEST_PASSWORD_8));
        users.add(createUser(TEST_NAME_9, TEST_LOGIN_9, TEST_PASSWORD_9));

        users.forEach(userService::saveUser);

        firstUserId = users.get(0).getId();
        lastUserId = users.get(users.size() - 1).getId();
    }

    @After
    public void tearDown() throws Exception {
        users.forEach(user -> userService.deleteUser(user.getId()));
    }

    @Test
    public void testAnd() {
        String name = TEST_NAME_4;
        String login = TEST_LOGIN_4;
        String password = TEST_PASSWORD_4;

        And and = new And(Arrays.asList(
                new Equals("name", name),
                new Equals("login", login),
                new Equals("password", password)));

        List<User> userList = userService.getUsers(and);

        assertEquals(1, userList.size());

        User user = userList.get(0);

        assertEquals(name, user.getName());
        assertEquals(login, user.getLogin());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testBetween() {
        String nameLo = TEST_NAME_3;
        String nameHi = TEST_NAME_6;
        Between between = new Between("name", nameLo, nameHi);

        List<User> userList = userService.getUsers(between);

        assertEquals(4, userList.size());
        userList.stream()
                .map(User::getName)
                .map(String::toLowerCase)
                .map(name -> name.compareTo(nameLo) >= 0 && name.compareTo(nameHi) <= 0)
                .forEach(Assert::assertTrue);
    }

    @Test
    public void testEq() {
        String name = TEST_NAME_5;

        Equals eq = new Equals("name", name);

        List<User> userList = userService.getUsers(eq);

        assertEquals(1, userList.size());
        assertEquals(name, userList.get(0).getName());
    }

    @Test
    public void testGreater() {
        String testPassword = TEST_PASSWORD_8;
        Greater greater = new Greater("password", testPassword);

        List<User> userList = userService.getUsers(greater);

        assertTrue(!userList.isEmpty());
        userList.stream()
                .map(User::getPassword)
                .map(String::toLowerCase)
                .map(pass -> pass.compareTo(testPassword) > 0)
                .forEach(Assert::assertTrue);
    }

    @Test
    public void testIn() {
        List<String> ids = new ArrayList<>();
        ids.add(firstUserId.toString());
        ids.add(firstUserId.add(BigDecimal.ONE).toString());
        ids.add(lastUserId.toString());
        ids.add(lastUserId.subtract(BigDecimal.ONE).toString());
        In in = new In("id", ids);

        List<User> userList = userService.getUsers(in);

        assertEquals(ids.size(), userList.size());
        userList.stream()
                .map(User::getId)
                .map(BigDecimal::toString)
                .map(String::toLowerCase)
                .map(ids::contains)
                .forEach(Assert::assertTrue);
    }

    @Test
    public void testLess() {
        String testLogin = TEST_LOGIN_3;
        Less less = new Less("login", testLogin);

        List<User> userList = userService.getUsers(less);

        assertTrue(!userList.isEmpty());
        userList.stream()
                .map(User::getLogin)
                .map(String::toLowerCase)
                .map(login -> login.compareTo(testLogin) < 0)
                .forEach(Assert::assertTrue);
    }

    @Test
    public void testLike() {
        Like like = new Like("password", "*st_pa*");

        List<User> userList = userService.getUsers(like);

        assertNotEquals(0, userList.size());
        userList.stream()
                .map(User::getPassword)
                .map(String::toLowerCase)
                .map(pass -> pass.matches(".*st_pa.*"))
                .forEach(Assert::assertTrue);
    }

    @Test
    public void testNot() {
        String name = TEST_NAME_3;
        Not not = new Not(new Equals("name", name));

        List<User> userList = userService.getUsers(not);

        assertNotEquals(0, userList.size());
        userList.stream()
                .map(User::getName)
                .map(String::toLowerCase)
                .map(name::equals)
                .forEach(Assert::assertFalse);
    }

    @Test
    public void testOr() {
        List<String> names = Arrays.asList(TEST_NAME_2, TEST_NAME_5, TEST_NAME_1);
        List<Condition> conditions = new ArrayList<>();
        names.forEach(name -> conditions.add(new Equals("name", name)));
        Or or = new Or(conditions);

        List<User> userList = userService.getUsers(or);

        assertEquals(names.size(), userList.size());
        userList.stream()
                .map(User::getName)
                .map(String::toLowerCase)
                .map(names::contains)
                .forEach(Assert::assertTrue);
    }

    private User createUser(String name, String login, String password) {
        User user = new User();
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        return user;
    }
}