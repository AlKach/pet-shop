package by.kachanov.shop.web.rest.controller;

import by.kachanov.shop.SpringWebTestSupport;
import org.flywaydb.core.internal.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public abstract class AbstractEntityCRUDTest extends SpringWebTestSupport {

    protected void doTestEntityCRUD() throws Exception {
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
        String entityUrl = getBasePath() + "/{id}";
        ResultActions createdEntity =
                mockMvc.perform(get(entityUrl, entityId).accept(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(status().isOk());
        
        for (Pair<String, Object> validation : getValidations()) {
            createdEntity.andExpect(jsonPath(validation.getLeft()).value(validation.getRight()));
        }

        String query = "";
        String queryUrl = getBasePath() + "?q=" + query;
        ResultActions entitiesByQuery =
                mockMvc.perform(get(queryUrl).accept(MediaType.APPLICATION_JSON_UTF8))
                        .andExpect(status().isOk());

        ResultActions entitiesByUrl = mockMvc.perform(get(getBasePath()).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        validateEntitiesList(entitiesByQuery, entityId);
        validateEntitiesList(entitiesByUrl, entityId);

        mockMvc.perform(delete(entityUrl, entityId)).andExpect(status().isNoContent());

        mockMvc.perform(get(entityUrl, entityId).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());

        mockMvc.perform(get(getBasePath()).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));
    }

    private void validateEntitiesList(ResultActions entitiesList, BigInteger entityId) throws Exception {
        entitiesList
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(entityId.intValue()));

        for (Pair<String, Object> validation : getValidations()) {
            entitiesList.andExpect(jsonPath(validation.getLeft().replace("$", "$[0]")).value(validation.getRight()));
        }
    }
    
    protected abstract Object getEntity();

    protected abstract String getBasePath();
    
    protected abstract List<Pair<String, Object>> getValidations();
}
