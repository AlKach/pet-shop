package by.kachanov.shop.service.security.voters;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class EmptyVoter implements AccessDecisionVoter {

    @Override
    public boolean supports(Class clazz) {
        return true;
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection collection) {
        return 0;
    }
}
