package by.kachanov.shop.dto.condition;

import java.util.List;

public class In extends MultiValueCondition {

    public In(String field, List<String> values) {
        super(field, values);
    }

}
