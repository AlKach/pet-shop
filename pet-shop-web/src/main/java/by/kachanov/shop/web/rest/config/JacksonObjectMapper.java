package by.kachanov.shop.web.rest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.stereotype.Component;

@Component
public class JacksonObjectMapper extends ObjectMapper {

    public JacksonObjectMapper() {
        Hibernate5Module hibernate5Module = new Hibernate5Module();
        hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
        registerModule(hibernate5Module);
    }

}
