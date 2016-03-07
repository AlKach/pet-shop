package by.kachanov.shop.dto.condition;

import java.util.List;

public class Between extends MultiValueCondition {

    public String getLow() {
        return getItemSafe(0);
    }

    public String getHigh() {
        return getItemSafe(1);
    }

    @Override
    public void setValues(List<String> values) {
        if (values.size() != 2) {
            throw new IllegalArgumentException("Between condition accepts excatly two values");
        }
        super.setValues(values);
    }

    private String getItemSafe(int index) {
        return values != null && values.size() == 2 ? values.get(index) : null;
    }
}
