package by.kachanov.shop.service.rsql;

import by.kachanov.shop.dto.condition.Condition;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.stereotype.Component;

@Component
public class RSQLParsingServiceImpl implements RSQLParsingService {
    @Override
    public Condition parseQuery(String query) {
        if (query == null || query.trim().isEmpty()) {
            return null;
        }

        Node root = new RSQLParser(Operators.ALL_OPERATORS).parse(query);
        return root.accept(new RSQLConditionVisitor());
    }
}
