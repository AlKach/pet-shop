package by.kachanov.shop.service.rsql;

import by.kachanov.shop.dto.condition.Condition;
import by.kachanov.shop.dto.condition.Equals;
import org.junit.Test;

import static org.fest.assertions.Assertions.*;

public class RSQLServiceTest {

    private static final String FIELD = "field";
    private static final String VALUE = "value";

    private RSQLParsingService parsingService = new RSQLParsingServiceImpl();

    @Test
    public void testEmptyQuery() {
        Condition condition = parsingService.parseQuery("");
        assertThat(condition).isNull();
    }

    @Test
    public void testEqualsQuery() {
        Condition condition = parsingService.parseQuery(FIELD + " == " + VALUE);
        assertThat(condition)
                .isNotNull()
                .isInstanceOf(Equals.class);
    }

}
