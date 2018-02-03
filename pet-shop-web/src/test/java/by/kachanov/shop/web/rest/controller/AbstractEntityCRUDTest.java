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

public abstract class AbstractEntityCRUDTest extends SpringWebTestSupport {

    protected void doTestEntityCRUDTest() throws Exception {
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
        ResultActions createdEntity =
                mockMvc.perform(get(getBasePath() + "/{id}", entityId).accept(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(status().isOk());
        
        for (Pair<String, Object> validation : getValidations()) {
            createdEntity.andExpect(jsonPath(validation.getLeft()).value(validation.getRight()));
        }

        ResultActions allEntities = mockMvc.perform(post(getBasePath() + "/query").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(entityId.intValue()));

        for (Pair<String, Object> validation : getValidations()) {
            allEntities.andExpect(jsonPath(validation.getLeft().replace("$", "$[0]")).value(validation.getRight()));
        }

        mockMvc.perform(delete(getBasePath() + "/{id}", entityId)).andExpect(status().isNoContent());

        mockMvc.perform(get(getBasePath() + "/{id}", entityId).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());

        mockMvc.perform(post(getBasePath() + "/query").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }
    
    protected abstract Object getEntity();

    protected abstract String getBasePath();
    
    protected abstract List<Pair<String, Object>> getValidations();
}
