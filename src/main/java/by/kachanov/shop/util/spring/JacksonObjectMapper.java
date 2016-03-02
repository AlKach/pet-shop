package by.kachanov.shop.util.spring;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

public class JacksonObjectMapper extends ObjectMapper {

    public JacksonObjectMapper() {
        Hibernate5Module hibernate5Module = new Hibernate5Module();
        hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
        registerModule(hibernate5Module);
    }

}
