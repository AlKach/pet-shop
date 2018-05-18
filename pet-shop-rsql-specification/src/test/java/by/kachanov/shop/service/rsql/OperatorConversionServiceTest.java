package by.kachanov.shop.service.rsql;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import by.kachanov.shop.service.rsql.converters.operator.ComparisonNodeConverter;
import by.kachanov.shop.service.rsql.converters.operator.NodeConverter;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.Node;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

@RunWith(MockitoJUnitRunner.class)
public class OperatorConversionServiceTest {

    @InjectMocks
    private OperatorConversionService operatorConversionService;

    @Spy
    private List<NodeConverter> converters = Operators.ALL_OPERATORS
            .stream()
            .map(op -> mock(ComparisonNodeConverter.class, withSettings().useConstructor(op)))
            .peek(c -> when(c.getOperator()).thenCallRealMethod())
            .peek(c -> when(c.supports(argThat(matcherFor(c)))).thenReturn(true))
            .peek(c -> when(c.convert(any(), any(), any())).thenReturn(mock(Predicate.class)))
            .collect(Collectors.toList());

    @Test
    public void convert() {
        ComparisonNode node = mock(ComparisonNode.class, withSettings().useConstructor(Operators.LESS, "=test=", Collections.singletonList("")));
        when(node.getOperator()).thenReturn(Operators.LESS);
        Root root = mock(Root.class);
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        NodeConverter converter = converters.stream()
                .filter(c -> c.supports(new ComparisonNode(Operators.LESS, "=test=", Collections.singletonList(""))))
                .findFirst()
                .orElse(mock(NodeConverter.class));

        operatorConversionService.convert(node, root, criteriaBuilder);

        verify(converter).convert(node, root, criteriaBuilder);
    }

    private ArgumentMatcher<Node> matcherFor(ComparisonNodeConverter converter) {
        return arg -> arg instanceof ComparisonNode && converter.getOperator().equals(((ComparisonNode) arg).getOperator());
    }
}