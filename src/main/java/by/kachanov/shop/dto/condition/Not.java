package by.kachanov.shop.dto.condition;

import java.util.Collection;

public class Not implements Condition {

    private Condition condition;

    public Not(Condition condition) {
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public Collection<String> getFields() {
        return condition.getFields();
    }
}
