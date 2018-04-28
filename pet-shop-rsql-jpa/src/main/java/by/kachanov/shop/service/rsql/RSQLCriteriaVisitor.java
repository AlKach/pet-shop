package by.kachanov.shop.service.rsql;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.NoArgRSQLVisitorAdapter;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.OrNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
@SuppressWarnings("unchecked")
public class RSQLCriteriaVisitor extends NoArgRSQLVisitorAdapter<Predicate> {

    private final CriteriaBuilder criteriaBuilder;

    @Autowired
    private TypeResolver typeResolver;

    @Autowired
    private ConversionService conversionService;

    private final Root root;

    public RSQLCriteriaVisitor(Root root, CriteriaBuilder criteriaBuilder) {
        this.root = root;
        this.criteriaBuilder = criteriaBuilder;
    }

    @Override
    public Predicate visit(AndNode node) {
        return criteriaBuilder.and(getRestrictions(node.getChildren()));
    }

    @Override
    public Predicate visit(OrNode node) {
        return criteriaBuilder.or(getRestrictions(node.getChildren()));
    }

    @Override
    public Predicate visit(ComparisonNode node) {
        ComparisonOperator operator = node.getOperator();
        String selector = node.getSelector();
        List<String> arguments = node.getArguments();
        Class<? extends Comparable> targetType =
                (Class<? extends Comparable>) typeResolver.resolveType(root.getJavaType(), selector);
        List<? extends Comparable> convertedArguments = arguments.stream()
                .map(arg -> conversionService.convert(arg, targetType))
                .collect(Collectors.toList());
        Comparable firstArgument = convertedArguments.get(0);
        Expression<Comparable<? super Comparable>> path = resolvePath(selector);
        if (Operators.EQUALS.equals(operator)) {
            return criteriaBuilder.equal(path, firstArgument);
        } else if (Operators.NOT_EQUALS.equals(operator)) {
            return criteriaBuilder.notEqual(path, firstArgument);
        } else if (Operators.GREATER.equals(operator)) {
            return criteriaBuilder.greaterThan(path, firstArgument);
        } else if (Operators.GREATER_OR_EQUALS.equals(operator)) {
            return criteriaBuilder.greaterThanOrEqualTo(path, firstArgument);
        } else if (Operators.LESS.equals(operator)) {
            return criteriaBuilder.lessThan(path, firstArgument);
        } else if (Operators.LESS_OR_EQUALS.equals(operator)) {
            return criteriaBuilder.lessThanOrEqualTo(path, firstArgument);
        } else if (Operators.LIKE.equals(operator)) {
            return criteriaBuilder.like(resolvePath(selector), arguments.get(0).replaceAll("\\*", "%"));
        } else if (Operators.NOT_LIKE.equals(operator)) {
            return criteriaBuilder.notLike(resolvePath(selector), arguments.get(0).replaceAll("\\*", "%"));
        } else if (Operators.IN.equals(operator)) {
            return resolvePath(selector).in(convertedArguments);
        } else if (Operators.NOT_IN.equals(operator)) {
            return resolvePath(selector).in(convertedArguments).not();
        } else if (Operators.BETWEEN.equals(operator)) {
            return criteriaBuilder.between(path, firstArgument, convertedArguments.get(1));
        } else if (Operators.NOT_BETWEEN.equals(operator)) {
            return criteriaBuilder.between(path, firstArgument, convertedArguments.get(1)).not();
        } else {
            throw new IllegalArgumentException("Unknown operator: " + operator.getSymbol());
        }
    }

    private Predicate[] getRestrictions(List<Node> children) {
        return children.stream().map(n -> n.accept(this)).collect(Collectors.toList()).toArray(new Predicate[]{});
    }

    private <T extends Comparable> Expression<T> resolvePath(String propertyPath) {
        List<String> parts = Arrays.asList(propertyPath.split("\\."));
        List<String> joins = parts.subList(0, parts.size() - 1);
        From currentRoot = this.root;
        for (String join : joins) {
            currentRoot = currentRoot.join(join);
        }

        return currentRoot.get(parts.get(parts.size() - 1));
    }
}
