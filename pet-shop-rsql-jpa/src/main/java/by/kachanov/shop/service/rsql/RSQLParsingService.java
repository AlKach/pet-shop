package by.kachanov.shop.service.rsql;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public interface RSQLParsingService {

    <T> CriteriaQuery<T> parseQuery(String query, Class<T> rootType, EntityManager entityManager);

}
