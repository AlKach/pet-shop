package by.kachanov.shop.service.rsql;

import by.kachanov.shop.dto.condition.Condition;

public interface RSQLParsingService {

    Condition parseQuery(String query);

}
