package by.kachanov.shop.dto.condition;

import java.util.List;

public abstract class JunctionCondition implements Condition {

    protected List<Expression> expressions;

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }
}
