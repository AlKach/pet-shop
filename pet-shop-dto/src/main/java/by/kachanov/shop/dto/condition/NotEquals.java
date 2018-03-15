package by.kachanov.shop.dto.condition;

public class NotEquals extends SingleValueCondition {

    public NotEquals() {}

    public NotEquals(String field, String value) {
        super(field, value);
    }

}
