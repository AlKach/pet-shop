package by.kachanov.shop.service.rsql;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import by.kachanov.shop.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfig.class)
public class TypeResolverTest {

    @Autowired
    private TypeResolver typeResolver;

    @Test
    public void testResolveDirectProperty() {
        assertEquals(String.class, typeResolver.resolveType(RootEntity.class, "name"));
    }

    @Test
    public void testResolvePrimitiveType() {
        assertEquals(long.class, typeResolver.resolveType(RootEntity.class, "id"));
    }

    @Test
    public void testResolveDirectArrayType() {
        assertEquals(double.class, typeResolver.resolveType(RootEntity.class, "points"));
    }

    @Test
    public void testResolveNestedProperty() {
        assertEquals(int.class, typeResolver.resolveType(RootEntity.class, "child.id"));
    }

    @Test
    public void testResolveDeepNestedProperty() {
        assertEquals(BigInteger.class, typeResolver.resolveType(RootEntity.class, "child.grandChild.id"));
    }

    @Test
    public void testResolveDeepNestedCyclicProperty() {
        assertEquals(String.class, typeResolver.resolveType(RootEntity.class, "child.grandChild.root.child.data"));
    }

    @Test
    public void testCollectionProperty() {
        assertEquals(GrandChildEntity.class, typeResolver.resolveType(RootEntity.class, "grandChildren"));
    }

    @Test
    public void testResolveNestedCollectionProperty() {
        assertEquals(RootEntity.class, typeResolver.resolveType(RootEntity.class, "child.grandChild.grandParents"));
    }

    @Test
    public void testResolveMultipleCollectionsInPathProperty() {
        assertEquals(int.class, typeResolver.resolveType(RootEntity.class, "grandChildren.parents.id"));
    }

    @Test
    public void testResolveNonExistentDirectProperty() {
        assertThrows(IllegalArgumentException.class, () -> typeResolver.resolveType(RootEntity.class, "nonExistent"));
    }

    @Test
    public void testNonExistentNestedProperty() {
        assertThrows(IllegalArgumentException.class, () -> typeResolver.resolveType(RootEntity.class, "child.grandChild.nonExistent"));
    }

    @Test
    public void testNonExistentCollectionProperty() {
        assertThrows(IllegalArgumentException.class, () -> typeResolver.resolveType(RootEntity.class, "grandChildren.nonExistent.id"));
    }

    @Test
    public void testInheritedOwnProperty() {
        assertEquals(char.class, typeResolver.resolveType(InheritedEntity.class, "letter"));
    }

    @Test
    public void testInheritedOverlappingProperty() {
        assertEquals(float.class, typeResolver.resolveType(InheritedEntity.class, "value"));
    }

    @Test
    public void testInheritedParentProperty() {
        assertEquals(ChildEntity.class, typeResolver.resolveType(InheritedEntity.class, "child"));
    }

    private static class RootEntity {
        private long id;
        private String name;
        private BigDecimal value;
        private double[] points;
        private ChildEntity child;
        private List<GrandChildEntity> grandChildren;
    }

    private static class ChildEntity {
        private int id;
        private String data;
        private GrandChildEntity grandChild;
    }

    private static class GrandChildEntity {
        private BigInteger id;
        RootEntity root;
        private Collection<ChildEntity> parents;
        private Set<RootEntity> grandParents;
    }

    private static class InheritedEntity extends RootEntity {
        private float value;
        private char letter;
    }

}
