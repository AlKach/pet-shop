package by.kachanov.shop.dto.condition;

import java.util.Arrays;
import java.util.List;

public class Between extends MultiValueCondition {

    public Between() {
    }

    public Between(String field, String low, String high) {
        super(field, Arrays.asList(low, high));
        checkValues(Arrays.asList(low, high));
    }

    public String getLow() {
        return getItemSafe(0);
    }

    public String getHigh() {
        return getItemSafe(1);
    }

    @Override
    public void setValues(List<String> values) {
        checkValues(values);
        super.setValues(values);
    }

    private void checkValues(List<String> values) {
        if (values.size() != 2) {
            throw new IllegalArgumentException("Between condition accepts exactly two values");
        }
    }

    private String getItemSafe(int index) {
        return values != null && values.size() == 2 ? values.get(index) : null;
    }
}
