package by.kachanov.shop.dto.condition;

public class Not implements Condition {

    private Condition condition;

    public Not() {
    }

    public Not(Condition condition) {
        this.condition = condition;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
