package by.kachanov.shop.web.rest.controller;

import by.kachanov.shop.dto.User;
import org.flywaydb.core.internal.util.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class UserCreationTest extends AbstractEntityCreationTestSupport {
    
    private static final String TEST_LOGIN = "test login";
    
    private static final String TEST_PASSWORD = "test password";
    
    private static final String TEST_NAME = "test name";

    @Test
    public void testCreation() throws Exception {
        doTestEntityCreation();
    }

    @Override
    protected Object getEntity() {
        User user = new User();
        user.setLogin(TEST_LOGIN);
        user.setPassword(TEST_PASSWORD);
        user.setName(TEST_NAME);
        return user;
    }

    @Override
    protected String getBasePath() {
        return "/users";
    }

    @Override
    protected List<Pair<String, Object>> getValidations() {
        return Arrays.asList(
                Pair.of("$.login", TEST_LOGIN),
                Pair.of("$.password", TEST_PASSWORD),
                Pair.of("$.name", TEST_NAME));
    }
}
