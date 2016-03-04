package by.kachanov.shop.dto.condition;

public abstract class SingleValueCondition implements Condition {

    protected String field;

    protected String value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
