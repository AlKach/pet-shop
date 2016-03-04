package by.kachanov.shop.dto.condition;

import java.util.List;

public class Between extends MultiValueCondition {
    @Override
    public void setValues(List<String> values) {
        if (values.size() != 2) {
            throw new IllegalArgumentException("Between condition accepts excatly two values");
        }
        super.setValues(values);
    }
}
