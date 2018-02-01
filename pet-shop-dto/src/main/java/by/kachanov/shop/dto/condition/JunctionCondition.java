package by.kachanov.shop.dto.condition;

import java.util.List;

public abstract class JunctionCondition implements Condition {

    private List<Condition> conditions;

    public JunctionCondition() {
    }

    public JunctionCondition(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }
}
