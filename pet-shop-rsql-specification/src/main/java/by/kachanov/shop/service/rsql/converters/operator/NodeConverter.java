package by.kachanov.shop.service.rsql.converters.operator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cz.jirutka.rsql.parser.ast.Node;

public interface NodeConverter {

    boolean supports(Node node);

    Predicate convert(Node node, Root root, CriteriaBuilder criteriaBuilder);

}
