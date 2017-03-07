package by.kachanov.shop.service.security.voters;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StaticAuthoritiesVoter extends AbstractVoter {

    @Override
    protected boolean hasPermission(Authentication authentication, BigDecimal objectId, ConfigAttribute attribute) {
        return authentication.getAuthorities().contains(new SimpleGrantedAuthority(attribute.toString()));
    }
}
