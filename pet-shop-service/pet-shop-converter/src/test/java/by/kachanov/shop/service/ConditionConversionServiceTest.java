package by.kachanov.shop.service;

import by.kachanov.shop.config.ServiceConfig;
import by.kachanov.shop.dto.User;
import by.kachanov.shop.dto.condition.*;
import by.kachanov.shop.config.RepositoryConfig;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static by.kachanov.shop.TestConstants.*;
import static org.junit.Assert.*;

@SpringBootTest(classes = {
        RepositoryConfig.class,
        ServiceConfig.class
})
@EnableAutoConfiguration(exclude = {
        JpaRepositoriesAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
@ActiveProfiles("test")
public class ConditionConversionServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserService userService;

    private List<User> users = new ArrayList<>();

    private BigInteger firstUserId = null;

    private BigInteger lastUserId = null;

    @Before
    public void setUp() {
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
    public void tearDown() {
        users.forEach(user -> userService.deleteUser(user.getId()));
    }

    @Test
    public void testAnd() {
        String name = TEST_NAME_4;
        String login = TEST_LOGIN_4;
        String password = TEST_PASSWORD_4;

        And and = new And(Arrays.asList(
                new Equals(PARAM_NAME, name),
                new Equals(PARAM_LOGIN, login),
                new Equals(PARAM_PASSWORD, password)));

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
        Between between = new Between(PARAM_NAME, nameLo, nameHi);

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

        Equals eq = new Equals(PARAM_NAME, name);

        List<User> userList = userService.getUsers(eq);

        assertEquals(1, userList.size());
        assertEquals(name, userList.get(0).getName());
    }

    @Test
    public void testExpressionActiveCondition() {
        Expression expression = new Expression();
        In in = new In();
        expression.setIn(in);
        assertEquals(in, expression.getActiveCondition());
    }

    @Test
    public void testExpressionEq() {
        String name = TEST_NAME_1;
        Expression expression = new Expression();
        expression.setEq(new Equals(PARAM_NAME, name));
        List<User> userList = userService.getUsers(expression);

        assertEquals(1, userList.size());
        assertEquals(name, userList.get(0).getName());
    }

    @Test
    public void testExpressionGreater() {
        String login = TEST_LOGIN_8;
        Expression expression = new Expression();
        expression.setGt(new Greater(PARAM_LOGIN, login));
        List<User> userList = userService.getUsers(expression);

        assertTrue(!userList.isEmpty());
        userList.stream()
                .map(User::getLogin)
                .map(String::toLowerCase)
                .map(pass -> pass.compareTo(login) > 0)
                .forEach(Assert::assertTrue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExpressionMultiple() {
        Expression expression = new Expression();
        expression.setIn(new In());
        expression.setLt(new Less());
    }

    @Test
    public void testGreater() {
        String testPassword = TEST_PASSWORD_8;
        Greater greater = new Greater(PARAM_PASSWORD, testPassword);

        List<User> userList = userService.getUsers(greater);

        assertTrue(!userList.isEmpty());
        userList.stream()
                .map(User::getPassword)
                .map(String::toLowerCase)
                .map(pass -> pass.compareTo(testPassword) > 0)
                .forEach(Assert::assertTrue);
    }

    @Test
    public void testGreaterOrEquals() {
        String testLogin = TEST_LOGIN_5;
        GreaterOrEquals ge = new GreaterOrEquals(PARAM_LOGIN, testLogin);

        List<User> userList = userService.getUsers(ge);

        assertTrue(!userList.isEmpty());
        userList.stream()
                .map(User::getLogin)
                .map(login -> login.compareTo(testLogin) >= 0)
                .forEach(Assert::assertTrue);
        userList.stream()
                .map(User::getLogin)
                .map(login -> login.compareTo(testLogin) < 0)
                .forEach(Assert::assertFalse);
    }

    @Test
    public void testIn() {
        List<String> ids = new ArrayList<>();
        ids.add(firstUserId.toString());
        ids.add(firstUserId.add(BigInteger.ONE).toString());
        ids.add(lastUserId.toString());
        ids.add(lastUserId.subtract(BigInteger.ONE).toString());
        In in = new In("id", ids);

        List<User> userList = userService.getUsers(in);

        assertEquals(ids.size(), userList.size());
        userList.stream()
                .map(User::getId)
                .map(BigInteger::toString)
                .map(String::toLowerCase)
                .map(ids::contains)
                .forEach(Assert::assertTrue);
    }

    @Test
    public void testLess() {
        String testLogin = TEST_LOGIN_3;
        Less less = new Less(PARAM_LOGIN, testLogin);

        List<User> userList = userService.getUsers(less);

        assertTrue(!userList.isEmpty());
        userList.stream()
                .map(User::getLogin)
                .map(String::toLowerCase)
                .map(login -> login.compareTo(testLogin) < 0)
                .forEach(Assert::assertTrue);
    }

    @Test
    public void testLessOrEquals() {
        String testName = TEST_NAME_4;
        LessOrEquals le = new LessOrEquals(PARAM_NAME, testName);

        List<User> userList = userService.getUsers(le);

        assertTrue(!userList.isEmpty());
        userList.stream()
                .map(User::getName)
                .map(login -> login.compareTo(testName) <= 0)
                .forEach(Assert::assertTrue);
        userList.stream()
                .map(User::getName)
                .map(login -> login.compareTo(testName) > 0)
                .forEach(Assert::assertFalse);
    }

    @Test
    public void testLike() {
        Like like = new Like(PARAM_PASSWORD, "*st_pa*");

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
        Not not = new Not(new Equals(PARAM_NAME, name));

        List<User> userList = userService.getUsers(not);

        assertNotEquals(0, userList.size());
        userList.stream()
                .map(User::getName)
                .map(String::toLowerCase)
                .map(name::equals)
                .forEach(Assert::assertFalse);
    }

    @Test
    public void testNe() {
        String name = TEST_NAME_6;
        NotEquals ne = new NotEquals(PARAM_NAME, name);

        List<User> usersList = userService.getUsers(ne);

        assertNotEquals(0, usersList.size());
        usersList.stream()
                .map(User::getName)
                .map(name::equalsIgnoreCase)
                .forEach(Assert::assertFalse);
    }

    @Test
    public void testOr() {
        List<String> names = Arrays.asList(TEST_NAME_2, TEST_NAME_5, TEST_NAME_1);
        List<Condition> conditions = new ArrayList<>();
        names.forEach(name -> conditions.add(new Equals(PARAM_NAME, name)));
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