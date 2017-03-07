package by.kachanov.shop.service.security.voters;

import by.kachanov.shop.dto.User;
import by.kachanov.shop.dto.security.Identifiable;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static by.kachanov.shop.dto.constants.SecurityConstants.ROLE_ONESELF;

@Component
public class OneselfRoleVoter extends AbstractVoter {

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return ROLE_ONESELF.equalsIgnoreCase(attribute.getAttribute());
    }

    @Override
    protected boolean hasPermission(Authentication authentication, BigDecimal objectId, ConfigAttribute attribute) {
        Object authDetails = authentication.getDetails();
        if (authDetails instanceof User) {
            User authenticatedUser = (User) authDetails;
            if (authenticatedUser.getId().equals(objectId)) {
                return true;
            }
        }

        return false;
    }

    @Override
    protected BigDecimal extractObjectIdentity(Object object) {
        BigDecimal objectId = super.extractObjectIdentity(object);
        if (objectId == null && object instanceof Identifiable) {
            objectId = ((Identifiable) object).getId();
        }

        return objectId;
    }
}
