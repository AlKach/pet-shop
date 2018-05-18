package by.kachanov.shop.service.rsql.converters.operator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import java.util.List;

import by.kachanov.shop.service.rsql.Operators;
import org.springframework.stereotype.Component;

@Component
public class EqualConverter extends ComparisonNodeConverter {

    public EqualConverter() {
        super(Operators.EQUAL);
    }

    @Override
    protected Predicate doConvert(Expression expression, List<Comparable> arguments, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(expression, arguments.get(0));
    }
}
