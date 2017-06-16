package by.kachanov.shop.dto.condition;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class JunctionCondition extends AbstractCondition {

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

    @Override
    public Collection<String> getFields() {
        return conditions.stream()
                .map(Condition::getFields)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
