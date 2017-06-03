package by.kachanov.shop.service.converter;

import by.kachanov.shop.SpringTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class ConversionContextHolderTest extends SpringTest {

    private ConversionContextHolder holder = ConversionContextHolder.getInstance();

    @Test
    public void testGetAliasPlain() throws Exception {
        assertEquals(holder.getAlias("field"), "field");
    }

    @Test
    public void testGetAliasNested() throws Exception {
        for (int i = 1; i < 10; i++) {
            String field = IntStream.range(0, i + 1).mapToObj(n -> "field" + n).collect(Collectors.joining("."));
            holder.resetAliases();
            assertEquals(holder.getAlias(field), "alias" + (i - 1) + ".field" + i);
        }
    }

    @Test
    public void testRegisteredAliases() throws Exception {
        String field = "a.b.c.d.e.f.g.h";
        holder.resetAliases();
        String alias = holder.getAlias(field);
        Map<String, String> registeredAliases = holder.getRegisteredAliases();
        assertEquals(registeredAliases.size(), 7);

        Map<String, String> reversedAliases = new HashMap<>();
        registeredAliases.forEach((k, v) -> reversedAliases.put(v, k));

        boolean replaced;
        do {
            replaced = false;
            for (String key : reversedAliases.keySet()) {
                replaced = replaced || alias.contains(key);
                alias = alias.replace(key, reversedAliases.get(key));
            }
        } while (replaced);

        assertEquals(field, alias);
    }

    @Test
    public void testRepeatingAliases() throws Exception {
        String field = "field";
        testRepeatingAliases(field);
    }

    @Test
    public void testRepeatingNestedAlias() throws Exception {
        String field = "outer.inner";
        testRepeatingAliases(field);
    }

    private void testRepeatingAliases(String field) {
        holder.resetAliases();

        String alias1 = holder.getAlias(field);
        String alias2 = holder.getAlias(field);
        String alias3 = holder.getAlias(field);

        assertEquals(alias1, alias2);
        assertEquals(alias1, alias3);
        assertEquals(alias2, alias3);
    }
}