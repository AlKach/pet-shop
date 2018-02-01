package by.kachanov.shop.dto.condition;

import java.util.List;

public class And extends JunctionCondition {

    public And(List<Condition> conditions) {
        super(conditions);
    }

}
