package by.kachanov.shop.service.rsql;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import by.kachanov.shop.dto.condition.And;
import by.kachanov.shop.dto.condition.Between;
import by.kachanov.shop.dto.condition.Condition;
import by.kachanov.shop.dto.condition.Equals;
import by.kachanov.shop.dto.condition.Greater;
import by.kachanov.shop.dto.condition.GreaterOrEquals;
import by.kachanov.shop.dto.condition.In;
import by.kachanov.shop.dto.condition.JunctionCondition;
import by.kachanov.shop.dto.condition.Less;
import by.kachanov.shop.dto.condition.LessOrEquals;
import by.kachanov.shop.dto.condition.Like;
import by.kachanov.shop.dto.condition.NotEquals;
import by.kachanov.shop.dto.condition.Or;
import by.kachanov.shop.dto.condition.SingleValueCondition;
import cz.jirutka.rsql.parser.ast.AbstractNode;
import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import org.junit.Test;

import static org.fest.assertions.Assertions.*;

public class RSQLConditionVisitorTest {
    
    private static final String SELECTOR1 = "selector1";
    private static final String SELECTOR2 = "selector2";
    private static final String SELECTOR3 = "selector3";
    private static final String VALUE1 = "value1";
    private static final String VALUE2 = "value2";
    private static final String VALUE3 = "value3";

    private RSQLConditionVisitor visitor = new RSQLConditionVisitor();
    
    @Test
    public void testEquals() {
        testSingleValueOperator(Operators.EQUALS, Equals.class);
    }

    @Test
    public void testNotEquals() {
        testSingleValueOperator(Operators.NOT_EQUALS, NotEquals.class);
    }

    @Test
    public void testGreater() {
        testSingleValueOperator(Operators.GREATER, Greater.class);
    }

    @Test
    public void testGreaterOrEquals() {
        testSingleValueOperator(Operators.GREATER_OR_EQUALS, GreaterOrEquals.class);
    }

    @Test
    public void testLess() {
        testSingleValueOperator(Operators.LESS, Less.class);
    }

    @Test
    public void testLessOrEquals() {
        testSingleValueOperator(Operators.LESS_OR_EQUALS, LessOrEquals.class);
    }

    @Test
    public void testLike() {
        testSingleValueOperator(Operators.LIKE, Like.class);
    }

    @Test
    public void testAnd() {
        testJunctionCondition(new AndNode(Collections.emptyList()), And.class);
    }

    @Test
    public void testOr() {
        testJunctionCondition(new OrNode(Collections.emptyList()), Or.class);
    }

    @Test
    public void testIn() {
        List<String> values = Arrays.asList(VALUE1, VALUE2, VALUE3);
        ComparisonNode node = new ComparisonNode(Operators.IN, SELECTOR1, values);

        In in = (In) performGenericConditionChecks(node, In.class);

        assertThat(in.getField())
                .isNotNull()
                .isEqualTo(SELECTOR1);

        assertThat(in.getValues())
                .isNotNull()
                .isNotEmpty()
                .hasSize(values.size());

        in.getValues().forEach(val -> assertThat(val).isIn(values));
    }

    @Test
    public void testBetween() {
        ComparisonNode node = new ComparisonNode(Operators.BETWEEN, SELECTOR1, Arrays.asList(VALUE1, VALUE2));
        Between between = (Between) performGenericConditionChecks(node, Between.class);

        assertThat(between.getField())
                .isNotNull()
                .isEqualTo(SELECTOR1);

        assertThat(between.getLow())
                .isNotNull()
                .isEqualTo(VALUE1);

        assertThat(between.getHigh())
                .isNotNull()
                .isEqualTo(VALUE2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalBetween() {
        ComparisonNode node = new ComparisonNode(Operators.BETWEEN, SELECTOR1, Arrays.asList(VALUE1, VALUE2, VALUE3));
        visitor.visit(node);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalOperator() {
        ComparisonNode node =
                new ComparisonNode(new ComparisonOperator("=illegal="), SELECTOR1, Collections.singletonList(VALUE1));
        visitor.visit(node);
    }

    private <T extends JunctionCondition> void testJunctionCondition(LogicalNode emptyLogicalNode,
            Class<T> conditionType) {
        List<ComparisonNode> conditions = Arrays.asList(
                new ComparisonNode(Operators.EQUALS, SELECTOR1, Collections.singletonList(VALUE1)),
                new ComparisonNode(Operators.GREATER, SELECTOR2, Collections.singletonList(VALUE2)),
                new ComparisonNode(Operators.LESS, SELECTOR3, Collections.singletonList(VALUE3)));
        LogicalNode node = emptyLogicalNode.withChildren(conditions);

        JunctionCondition condition = (JunctionCondition) performGenericConditionChecks(node, conditionType);

        performChildConditionTest(condition.getConditions(), Equals.class, SELECTOR1, VALUE1);
        performChildConditionTest(condition.getConditions(), Greater.class, SELECTOR2, VALUE2);
        performChildConditionTest(condition.getConditions(), Less.class, SELECTOR3, VALUE3);
    }

    private <T extends SingleValueCondition> void performChildConditionTest(List<Condition> childConditions,
            Class<T> conditionType, String field, String value) {
        Condition optionalCondition =
                childConditions.stream().filter(c -> conditionType.equals(c.getClass())).findFirst().orElse(null);

        assertThat(optionalCondition)
                .isNotNull()
                .isInstanceOf(conditionType);

        if (optionalCondition != null) {
            performSingleValueConditionChecks((SingleValueCondition) optionalCondition, field, value);
        }
    }
    
    private <T extends SingleValueCondition> void testSingleValueOperator(ComparisonOperator rsqlOperator,
            Class<T> conditionType) {
        ComparisonNode node = new ComparisonNode(rsqlOperator, SELECTOR1, Collections.singletonList(VALUE1));

        SingleValueCondition singleValueCondition = (SingleValueCondition) performGenericConditionChecks(node, conditionType);

        performSingleValueConditionChecks(singleValueCondition, SELECTOR1, VALUE1);
    }
    
    private void performSingleValueConditionChecks(SingleValueCondition singleValueCondition, String field,
            String value) {
        assertThat(singleValueCondition.getField())
                .isNotNull()
                .isEqualTo(field);

        assertThat(singleValueCondition.getValue())
                .isNotNull()
                .isEqualTo(value);
    }

    private <T extends Condition> Condition performGenericConditionChecks(AbstractNode node, Class<T> conditionType) {
        Condition condition = node.accept(visitor);

        assertThat(condition)
                .isNotNull()
                .isInstanceOf(conditionType);

        return condition;
    }

}