package by.kachanov.shop.dto.condition;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Expression implements Condition {

    private And and;

    private Between between;

    private Equals eq;

    private Greater gt;

    private In in;

    private Less lt;

    private Like like;

    private Not not;

    private Or or;

    @JsonIgnore
    private Condition activeCondition;

    public And getAnd() {
        return and;
    }

    public void setAnd(And and) {
        setActiveCondition(and);
        this.and = and;
    }

    public Between getBetween() {
        return between;
    }

    public void setBetween(Between between) {
        setActiveCondition(between);
        this.between = between;
    }

    public Equals getEq() {
        return eq;
    }

    public void setEq(Equals eq) {
        setActiveCondition(eq);
        this.eq = eq;
    }

    public Greater getGt() {
        return gt;
    }

    public void setGt(Greater gt) {
        setActiveCondition(gt);
        this.gt = gt;
    }

    public In getIn() {
        return in;
    }

    public void setIn(In in) {
        setActiveCondition(in);
        this.in = in;
    }

    public Less getLt() {
        return lt;
    }

    public void setLt(Less lt) {
        setActiveCondition(lt);
        this.lt = lt;
    }

    public Like getLike() {
        return like;
    }

    public void setLike(Like like) {
        setActiveCondition(like);
        this.like = like;
    }

    public Not getNot() {
        return not;
    }

    public void setNot(Not not) {
        setActiveCondition(not);
        this.not = not;
    }

    public Or getOr() {
        return or;
    }

    public void setOr(Or or) {
        setActiveCondition(or);
        this.or = or;
    }

    public Condition getActiveCondition() {
        return activeCondition;
    }

    private void setActiveCondition(Condition activeCondition) {
        if (this.activeCondition != null) {
            throw new IllegalArgumentException("Only one condition can be active in expression");
        }
        this.activeCondition = activeCondition;
    }
}
