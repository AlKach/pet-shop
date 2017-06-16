package by.kachanov.shop.service.converter;

import by.kachanov.shop.SpringTest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class ConversionContextHolderTest extends SpringTest {

    private static final String FIELD_NAME = "field";

    private ConversionContextHolder holder = ConversionContextHolder.getInstance();

    @Test
    public void testGetAliasPlain() throws Exception {
        try (ConversionContextHolder holder = ConversionContextHolder.getInstance()) {
            assertEquals(holder.getAlias(FIELD_NAME), FIELD_NAME);
        }
    }

    @Test
    public void testGetAliasNested() throws Exception {
        for (int i = 1; i < 10; i++) {
            String field = IntStream.range(0, i + 1).mapToObj(n -> FIELD_NAME + n).collect(Collectors.joining("."));
            try (ConversionContextHolder holder = ConversionContextHolder.getInstance()) {
                assertEquals(holder.getAlias(field), "alias" + (i - 1) + "." + FIELD_NAME + i);
            }
        }
    }

    @Test
    public void testRegisteredAliases() throws Exception {
        String field = "a.b.c.d.e.f.g.h";
        try (ConversionContextHolder holder = ConversionContextHolder.getInstance()) {
            String fieldAlias = holder.getAlias(field);
            Map<String, String> registeredAliases = holder.getRegisteredAliases();
            assertEquals(registeredAliases.size(), 7);

            Map<String, String> reversedAliases = new HashMap<>();
            registeredAliases.forEach((k, v) -> reversedAliases.put(v, k));

            boolean replaced;
            do {
                replaced = false;
                for (Map.Entry<String, String> entry : reversedAliases.entrySet()) {
                    String alias = entry.getKey();
                    String originalField = entry.getValue();
                    replaced = replaced || fieldAlias.contains(alias);
                    fieldAlias = fieldAlias.replace(alias, originalField);
                }
            } while (replaced);

            assertEquals(field, fieldAlias);
        }
    }

    @Test
    public void testRepeatingAliases() throws Exception {
        testRepeatingAliases(FIELD_NAME);
    }

    @Test
    public void testRepeatingNestedAlias() throws Exception {
        String field = "outer.inner";
        testRepeatingAliases(field);
    }

    private void testRepeatingAliases(String field) {
        try (ConversionContextHolder holder = ConversionContextHolder.getInstance()) {
            String alias1 = holder.getAlias(field);
            String alias2 = holder.getAlias(field);
            String alias3 = holder.getAlias(field);

            assertEquals(alias1, alias2);
            assertEquals(alias1, alias3);
            assertEquals(alias2, alias3);
        }

    }
}