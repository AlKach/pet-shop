package by.kachanov.shop.service.rsql;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.NoArgRSQLVisitorAdapter;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.OrNode;
import org.springframework.beans.factory.annotation.Autowired;

public class RSQLPredicateVisitor extends NoArgRSQLVisitorAdapter<Predicate> {

    private final CriteriaBuilder criteriaBuilder;

    @Autowired
    private OperatorConversionService operatorConversionService;

    private final Root root;

    public RSQLPredicateVisitor(Root root, CriteriaBuilder criteriaBuilder) {
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
        return operatorConversionService.convert(node, root, criteriaBuilder);
    }

    private Predicate[] getRestrictions(List<Node> children) {
        return children.stream().map(n -> n.accept(this)).collect(Collectors.toList()).toArray(new Predicate[]{});
    }
}
