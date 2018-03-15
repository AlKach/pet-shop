package by.kachanov.shop.service.rsql;

import java.util.List;
import java.util.stream.Collectors;

import by.kachanov.shop.dto.condition.And;
import by.kachanov.shop.dto.condition.Between;
import by.kachanov.shop.dto.condition.Condition;
import by.kachanov.shop.dto.condition.Equals;
import by.kachanov.shop.dto.condition.Greater;
import by.kachanov.shop.dto.condition.GreaterOrEquals;
import by.kachanov.shop.dto.condition.In;
import by.kachanov.shop.dto.condition.Less;
import by.kachanov.shop.dto.condition.LessOrEquals;
import by.kachanov.shop.dto.condition.Like;
import by.kachanov.shop.dto.condition.NotEquals;
import by.kachanov.shop.dto.condition.Or;
import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.NoArgRSQLVisitorAdapter;
import cz.jirutka.rsql.parser.ast.OrNode;

class RSQLConditionVisitor extends NoArgRSQLVisitorAdapter<Condition> {

    @Override
    public Condition visit(AndNode node) {
        return new And(node.getChildren().stream().map(n -> n.accept(this)).collect(Collectors.toList()));
    }

    @Override
    public Condition visit(OrNode node) {
        return new Or(node.getChildren().stream().map(n -> n.accept(this)).collect(Collectors.toList()));
    }

    @Override
    public Condition visit(ComparisonNode node) {
        ComparisonOperator operator = node.getOperator();
        String selector = node.getSelector();
        List<String> arguments = node.getArguments();
        if (Operators.EQUALS.equals(operator)) {
            return new Equals(selector, arguments.get(0));
        } else if (Operators.NOT_EQUALS.equals(operator)) {
            return new NotEquals(selector, arguments.get(0));
        } else if (Operators.GREATER.equals(operator)) {
            return new Greater(selector, arguments.get(0));
        } else if (Operators.GREATER_OR_EQUALS.equals(operator)) {
            return new GreaterOrEquals(selector, arguments.get(0));
        } else if (Operators.LESS.equals(operator)) {
            return new Less(selector, arguments.get(0));
        } else if (Operators.LESS_OR_EQUALS.equals(operator)) {
            return new LessOrEquals(selector, arguments.get(0));
        } else if (Operators.LIKE.equals(operator)) {
            return new Like(selector, arguments.get(0));
        } else if (Operators.IN.equals(operator)) {
            return new In(selector, arguments);
        } else if (Operators.BETWEEN.equals(operator)) {
            if (arguments.size() != 2) {
                throw new IllegalArgumentException("=between= operator requires exactly two arguments");
            }

            return new Between(selector, arguments.get(0), arguments.get(1));
        } else {
            throw new IllegalArgumentException("Unknown operator: " + operator.getSymbol());
        }
    }

}
