package by.kachanov.shop.dto.condition;

public class GreaterOrEquals extends SingleValueCondition {
    public GreaterOrEquals() {
    }

    public GreaterOrEquals(String field, String value) {
        super(field, value);
    }
}
