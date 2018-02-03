package by.kachanov.shop.web.rest.controller;

import by.kachanov.shop.SpringWebTestSupport;
import org.flywaydb.core.internal.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public abstract class AbstractEntityCreationTestSupport extends SpringWebTestSupport {

    protected void doTestEntityCreation() throws Exception {
        Object entity = getEntity();
        assertNotNull(entity);

        MvcResult result = mockMvc.perform(
                post(getBasePath()).contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsBytes(entity))
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andReturn();

        String contentAsString = result.getResponse().getContentAsString();
        assertNotNull(contentAsString);

        BigInteger entityId = null;
        try {
            entityId = new BigInteger(contentAsString);
        } catch (NumberFormatException ex) {
            fail("Returned value is not valid id: " + contentAsString);
        }

        assertNotNull(entityId);
        ResultActions resultActions =
                mockMvc.perform(get(getBasePath() + "/{id}", entityId).accept(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(status().isOk());
        
        for (Pair<String, Object> validation : getValidations()) {
            resultActions.andExpect(jsonPath(validation.getLeft()).value(validation.getRight()));
        }
    }
    
    protected abstract Object getEntity();

    protected abstract String getBasePath();
    
    protected abstract List<Pair<String, Object>> getValidations();
}
