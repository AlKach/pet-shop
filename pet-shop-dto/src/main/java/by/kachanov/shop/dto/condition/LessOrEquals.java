package by.kachanov.shop.dto.condition;

public class LessOrEquals extends SingleValueCondition {
    public LessOrEquals() {
    }

    public LessOrEquals(String field, String value) {
        super(field, value);
    }
}
