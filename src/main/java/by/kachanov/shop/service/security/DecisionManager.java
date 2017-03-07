package by.kachanov.shop.service.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import static org.springframework.security.access.AccessDecisionVoter.ACCESS_GRANTED;

@Component("decisionManager")
public class DecisionManager extends AbstractAccessDecisionManager {

    private static final Logger LOGGER = Logger.getLogger(DecisionManager.class);

    @Autowired
    protected DecisionManager(List<AccessDecisionVoter<?>> decisionVoters) {
        super(decisionVoters);
    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes.isEmpty()) {
            return;
        }

        for (AccessDecisionVoter voter : getDecisionVoters()) {
            int decision = voter.vote(authentication, object, configAttributes);
            if (decision == ACCESS_GRANTED) {
                return;
            }
        }

        throw new AccessDeniedException("You don't have permission to perform this action");
    }

}
