package by.kachanov.shop.service.rsql;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.RSQLOperators;

public final class Operators {

    public static final ComparisonOperator EQUAL = RSQLOperators.EQUAL;
    public static final ComparisonOperator NOT_EQUAL = RSQLOperators.NOT_EQUAL;
    public static final ComparisonOperator GREATER = RSQLOperators.GREATER_THAN;
    public static final ComparisonOperator GREATER_OR_EQUAL = RSQLOperators.GREATER_THAN_OR_EQUAL;
    public static final ComparisonOperator LESS = RSQLOperators.LESS_THAN;
    public static final ComparisonOperator LESS_OR_EQUAL = RSQLOperators.LESS_THAN_OR_EQUAL;
    public static final ComparisonOperator IN = RSQLOperators.IN;
    public static final ComparisonOperator NOT_IN = RSQLOperators.NOT_IN;
    public static final ComparisonOperator LIKE = new ComparisonOperator("=lk=");
    public static final ComparisonOperator NOT_LIKE = new ComparisonOperator("=nlk=");
    public static final ComparisonOperator BETWEEN = new ComparisonOperator("=bw=", true);
    public static final ComparisonOperator NOT_BETWEEN = new ComparisonOperator("=nbw=", true);

    public static final Set<ComparisonOperator> ALL_OPERATORS = Collections.unmodifiableSet(
            new HashSet<>(
                    Arrays.asList(
                            EQUAL,
                            NOT_EQUAL,
                            GREATER,
                            GREATER_OR_EQUAL,
                            LESS,
                            LESS_OR_EQUAL,
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
