package by.kachanov.shop.service.rsql;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.RSQLOperators;

final class Operators {

    public static final ComparisonOperator
            EQUALS = RSQLOperators.EQUAL,
            NOT_EQUALS = RSQLOperators.NOT_EQUAL,
            GREATER = RSQLOperators.GREATER_THAN,
            GREATER_OR_EQUALS = RSQLOperators.GREATER_THAN_OR_EQUAL,
            LESS = RSQLOperators.LESS_THAN,
            LESS_OR_EQUALS = RSQLOperators.LESS_THAN_OR_EQUAL,
            IN = RSQLOperators.IN,
            NOT_IN = RSQLOperators.NOT_IN,
            LIKE = new ComparisonOperator("=lk="),
            NOT_LIKE = new ComparisonOperator("=nlk="),
            BETWEEN = new ComparisonOperator("=bw=", true),
            NOT_BETWEEN = new ComparisonOperator("=nbw=", true);

    public static final Set<ComparisonOperator> ALL_OPERATORS = Collections.unmodifiableSet(
            new HashSet<>(
                    Arrays.asList(
                            EQUALS,
                            NOT_EQUALS,
                            GREATER,
                            GREATER_OR_EQUALS,
                            LESS,
                            LESS_OR_EQUALS,
                            IN,
                            NOT_IN,
                            LIKE,
                            NOT_LIKE,
                            BETWEEN,
                            NOT_BETWEEN
                    )
            )
    );

    private Operators() {}

}
