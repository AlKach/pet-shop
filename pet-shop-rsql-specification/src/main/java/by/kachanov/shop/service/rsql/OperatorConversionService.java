package by.kachanov.shop.service.rsql;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import java.util.Collection;

import by.kachanov.shop.service.rsql.converters.operator.NodeConverter;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperatorConversionService {
    
    @Autowired
    private Collection<NodeConverter> converters;
    
    public Predicate convert(Node node, Root root, CriteriaBuilder criteriaBuilder) {
        return converters.stream()
                .filter(c -> c.supports(node))
                .findFirst()
                .map(c -> c.convert(node, root, criteriaBuilder))
                .orElseThrow(() -> new IllegalArgumentException("No supported converters for " + node));
    }
    
}
