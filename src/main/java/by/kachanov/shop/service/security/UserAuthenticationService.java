package by.kachanov.shop.service.security;

import by.kachanov.shop.dto.User;
import by.kachanov.shop.dto.condition.Equals;
import by.kachanov.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component("userDetailsService")
public class UserAuthenticationService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        List<User> users = userService.getUsers(new Equals("login", login));
        if (users.size() == 1) {
            User user = users.get(0);
            return new AuthenticatedUser(user.getLogin(), user.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("No user with login '" + login + "'");
        }
    }

    private static final class AuthenticatedUser extends org.springframework.security.core.userdetails.User {

        public AuthenticatedUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
        }

    }
}
