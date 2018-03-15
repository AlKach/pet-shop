package by.kachanov.shop.web.rest.controller;

import by.kachanov.shop.dto.condition.Condition;
import by.kachanov.shop.service.rsql.RSQLParsingService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController {

    @Autowired
    private RSQLParsingService parsingService;

    protected Condition parseQuery(String query) {
        return parsingService.parseQuery(query);
    }

}
