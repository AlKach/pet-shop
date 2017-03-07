package by.kachanov.shop.service.security.voters;

import by.kachanov.shop.dto.security.Identifiable;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static by.kachanov.shop.dto.constants.SecurityConstants.ROLE_OWNER;

@Component
public class OwnerRoleVoter extends OneselfRoleVoter {
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return ROLE_OWNER.equalsIgnoreCase(attribute.getAttribute());
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
