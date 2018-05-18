package by.kachanov.shop.service.rsql.converters.operator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import by.kachanov.shop.service.rsql.JpaParameterConversionService;
import by.kachanov.shop.service.rsql.TypeResolver;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ComparisonNodeConverter implements NodeConverter {

    @Autowired
    private TypeResolver typeResolver;

    @Autowired
    private JpaParameterConversionService conversionService;

    private final ComparisonOperator operator;

    protected ComparisonNodeConverter(ComparisonOperator operator) {
        this.operator = operator;
    }

    @Override
    public final boolean supports(Node node) {
        return node instanceof ComparisonNode && operator.equals(((ComparisonNode) node).getOperator());
    }

    @Override
    public final Predicate convert(Node node, Root root, CriteriaBuilder criteriaBuilder) {
        ComparisonNode comparisonNode = (ComparisonNode) node;
        String selector = comparisonNode.getSelector();
        List<Comparable> arguments = convertArguments(root, selector, comparisonNode.getArguments());
        Expression<Comparable> expression = resolvePath(root, selector);
        return doConvert(expression, arguments, criteriaBuilder);
    }

    private <T extends Comparable> List<T> convertArguments(Root root, String selector, List<String> arguments) {
        Class<T> targetType = (Class<T>) typeResolver.resolveType(root.getJavaType(), selector);
        return arguments.stream().map(arg -> conversionService.convert(arg, targetType)).collect(Collectors.toList());
    }

    private <T extends Comparable> Expression<T> resolvePath(Root root, String propertyPath) {
        List<String> parts = Arrays.asList(propertyPath.split("\\."));
        List<String> joins = parts.subList(0, parts.size() - 1);
        From currentRoot = root;
        for (String join : joins) {
            currentRoot = currentRoot.join(join);
        }

        return currentRoot.get(parts.get(parts.size() - 1));
    }

    protected abstract Predicate doConvert(Expression expression, List<Comparable> arguments, CriteriaBuilder criteriaBuilder);
}
