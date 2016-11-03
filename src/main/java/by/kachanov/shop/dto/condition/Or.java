package by.kachanov.shop.dto.condition;

import java.util.List;

public class Or extends JunctionCondition {

    public Or(List<Condition> conditions) {
        super(conditions);
    }

}
