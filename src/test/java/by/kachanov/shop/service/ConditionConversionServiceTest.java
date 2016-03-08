package by.kachanov.shop.service;

import by.kachanov.shop.SpringTest;
import by.kachanov.shop.dto.User;
import by.kachanov.shop.dto.condition.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ConditionConversionServiceTest extends SpringTest {

    @Autowired
    private UserService userService;

    private List<User> users = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        users.add(createUser("test_name1", "test_login1", "test_password1"));
        users.add(createUser("test_name2", "test_login2", "test_password2"));
        users.add(createUser("test_name3", "test_login3", "test_password3"));
        users.add(createUser("test_name4", "test_login4", "test_password4"));
        users.add(createUser("test_name5", "test_login5", "test_password5"));
        users.add(createUser("test_name6", "test_login6", "test_password6"));
        users.add(createUser("test_name7", "test_login7", "test_password7"));
        users.add(createUser("test_name8", "test_login8", "test_password8"));
        users.add(createUser("test_name9", "test_login9", "test_password9"));

        users.forEach(userService::saveUser);
    }

    @After
    public void tearDown() throws Exception {
        users.forEach(user -> userService.deleteUser(user.getId()));
    }

    @Test
    public void testAnd() {
        String name = "test_name4";
        String login = "test_login4";
        String password = "test_password4";

        Expression eqName = createEqExpr("name", name);
        Expression eqLogin = createEqExpr("login", login);
        Expression eqPassword = createEqExpr("password", password);

        And and = new And();
        and.setExpressions(Arrays.asList(eqName, eqLogin, eqPassword));

        Expression expr = new Expression();
        expr.setAnd(and);

        List<User> users = userService.getUsers(expr);

        assertEquals(1, users.size());

        User user = users.get(0);

        assertEquals(name, user.getName());
        assertEquals(login, user.getLogin());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testBetween() {
        String nameLo = "test_name3";
        String nameHi = "test_name6";
        Between between = new Between();
        between.setField("name");
        between.setValues(Arrays.asList(nameLo, nameHi));

        Expression expr = new Expression();
        expr.setBetween(between);

        List<User> users = userService.getUsers(expr);

        assertEquals(4, users.size());
        users.stream()
                .map(User::getName)
                .map(String::toLowerCase)
                .map(name -> name.compareTo(nameLo) >= 0 && name.compareTo(nameHi) <= 0)
                .forEach(Assert::assertTrue);
    }

    @Test
    public void testEq() {
        String name = "test_name5";

        Equals eq = new Equals();
        eq.setField("name");
        eq.setValue(name);

        Expression expr = new Expression();
        expr.setEq(eq);

        List<User> users = userService.getUsers(expr);

        assertEquals(1, users.size());
        assertEquals(name, users.get(0).getName());
    }

    @Test
    public void testGreater() {
        String testPassword = "test_password8";
        Greater greater = new Greater();
        greater.setField("password");
        greater.setValue(testPassword);

        Expression expr = new Expression();
        expr.setGt(greater);

        List<User> users = userService.getUsers(expr);

        assertTrue(users.size() >= 1);
        users.stream()
                .map(User::getPassword)
                .map(String::toLowerCase)
                .map(pass -> pass.compareTo(testPassword) > 0)
                .forEach(Assert::assertTrue);
    }

    @Test
    public void testIn() {
        List<String> logins = Arrays.asList("test_login3", "test_login5", "test_login7", "test_login1");
        In in = new In();
        in.setField("login");
        in.setValues(logins);

        Expression expr = new Expression();
        expr.setIn(in);

        List<User> users = userService.getUsers(expr);

        assertEquals(logins.size(), users.size());
        users.stream()
                .map(User::getLogin)
                .map(String::toLowerCase)
                .map(logins::contains)
                .forEach(Assert::assertTrue);
    }

    @Test
    public void testLess() {
        String testLogin = "test_login3";
        Less less = new Less();
        less.setField("login");
        less.setValue(testLogin);

        Expression expr = new Expression();
        expr.setLt(less);

        List<User> users = userService.getUsers(expr);

        assertTrue(users.size() >= 1);
        users.stream()
                .map(User::getLogin)
                .map(String::toLowerCase)
                .map(login -> login.compareTo(testLogin) < 0)
                .forEach(Assert::assertTrue);
    }

    @Test
    public void testLike() {
        Like like = new Like();
        like.setField("password");
        like.setValue("%st_pa%");

        Expression expr = new Expression();
        expr.setLike(like);

        List<User> users = userService.getUsers(expr);

        assertNotEquals(0, users.size());
        users.stream()
                .map(User::getPassword)
                .map(String::toLowerCase)
                .map(pass -> pass.matches(".*st_pa.*"))
                .forEach(Assert::assertTrue);
    }

    @Test
    public void testNot() {
        String name = "test_name3";
        Expression eqName = createEqExpr("name", name);

        Not not = new Not();
        not.setExpression(eqName);

        Expression expr = new Expression();
        expr.setNot(not);

        List<User> users = userService.getUsers(expr);

        assertNotEquals(0, users.size());
        users.stream()
                .map(User::getName)
                .map(String::toLowerCase)
                .map(name::equals)
                .forEach(Assert::assertFalse);
    }

    @Test
    public void testOr() {
        List<String> names = Arrays.asList("test_name2", "test_name5", "test_name1");
        List<Expression> expressions = new ArrayList<>();
        names.forEach(name -> expressions.add(createEqExpr("name", name)));

        Or or = new Or();
        or.setExpressions(expressions);

        Expression expr = new Expression();
        expr.setOr(or);

        List<User> users = userService.getUsers(expr);

        assertEquals(names.size(), users.size());
        users.stream()
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

    private Expression createEqExpr(String field, String value) {
        Equals eq = new Equals();
        eq.setField(field);
        eq.setValue(value);

        Expression expr = new Expression();
        expr.setEq(eq);

        return expr;
    }
}