package by.kachanov.shop.service.security;

import by.kachanov.shop.dto.User;
import by.kachanov.shop.dto.condition.Equals;
import by.kachanov.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("authenticationProvider")
public class UserAuthenticationService implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<User> users = userService.getUsers(new Equals("login", login));
        if (users.size() == 1) {
            User user = users.get(0);
            UsernamePasswordAuthenticationToken authenticatedUser =
                    new UsernamePasswordAuthenticationToken(login, password, new ArrayList<>());
            authenticatedUser.setDetails(user);
            return authenticatedUser;
        } else {
            throw new UsernameNotFoundException("No user with login '" + login + "'");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
