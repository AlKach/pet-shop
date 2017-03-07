package by.kachanov.shop.service.security.voters;

import by.kachanov.shop.dto.security.Identifiable;
import by.kachanov.shop.dto.security.Identifier;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

public abstract class AbstractVoter implements AccessDecisionVoter<Object> {

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class clazz) {
        return true;
    }

    @Override
    public final int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        int denied = 0;

        for (ConfigAttribute attribute : attributes) {
            if (!this.supports(attribute)) {
                continue;
            }

            BigDecimal objectId = getObjectId(object);
            if (hasPermission(authentication, objectId, attribute)) {
                return ACCESS_GRANTED;
            } else {
                denied++;
            }
        }

        return denied > 0 ? ACCESS_DENIED : ACCESS_ABSTAIN;
    }

    protected abstract boolean hasPermission(Authentication authentication, BigDecimal objectId, ConfigAttribute attribute);

    private BigDecimal getObjectId(Object object) {
        BigDecimal objectId = null;
        if (object instanceof MethodInvocation) {
            MethodInvocation invocation = (MethodInvocation) object;
            Class<?>[] parameterTypes = invocation.getMethod().getParameterTypes();
            Annotation[][] parameterAnnotations = invocation.getMethod().getParameterAnnotations();
            Object[] arguments = invocation.getArguments();
            boolean hasDomainObjectAnnotation = Arrays.stream(parameterAnnotations)
                    .flatMap(Arrays::stream)
                    .anyMatch(a -> Identifier.class.isAssignableFrom(a.annotationType()));

            if (hasDomainObjectAnnotation) {
                for (int i = 0; i < parameterAnnotations.length; i++) {
                    if (Arrays.stream(parameterAnnotations[i])
                            .anyMatch(a -> Identifier.class.isAssignableFrom(a.annotationType()))) {
                        objectId = extractObjectIdentity(arguments[i]);
                        break;
                    }
                }
            } else {
                for (int i = 0; i < parameterTypes.length; i++) {
                    objectId = extractObjectIdentity(arguments[i]);
                    if (objectId != null) {
                        break;
                    }
                }
            }
        } else {
            objectId = extractObjectIdentity(object);
        }

        return objectId;
    }

    protected BigDecimal extractObjectIdentity(Object object) {
        if (object == null) {
            return null;
        } else if (object instanceof BigDecimal) {
            return (BigDecimal) object;
        } else {
            return null;
        }
    }

}
