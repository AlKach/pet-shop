package by.kachanov.shop.service.converter;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConversionContextHolder implements AutoCloseable {

    private ThreadLocal<Map<String, String>> registeredAliases = new ThreadLocal<>();

    private ThreadLocal<Integer> aliasCount = new ThreadLocal<>();

    private ConversionContextHolder() {

    }

    private static class ConversionContextHolderInternal {
        private ConversionContextHolderInternal() {}
        private static final ConversionContextHolder INSTANCE = new ConversionContextHolder();
    }

    public static ConversionContextHolder getInstance() {
        return ConversionContextHolderInternal.INSTANCE;
    }

    public String getAlias(String field) {
        String resolvedAlias = field;
        Map<String, String> aliases = registeredAliases.get();
        if (aliases == null) {
            aliases = new LinkedHashMap<>();
            registeredAliases.set(aliases);
            aliasCount.set(0);
        }

        if (resolvedAlias.contains(".")) {
            int firstDotPosition;
            int secondDotPosition = resolvedAlias.indexOf('.');
            while (secondDotPosition != -1) {
                String fieldForAlias = resolvedAlias.substring(0, secondDotPosition);
                if (!aliases.containsKey(fieldForAlias)) {
                    Integer count = aliasCount.get();
                    aliasCount.set(count + 1);
                    String alias = "alias" + count;
                    aliases.put(fieldForAlias, alias);
                    resolvedAlias = alias + resolvedAlias.substring(secondDotPosition);
                } else {
                    resolvedAlias = resolvedAlias.replace(fieldForAlias, aliases.get(fieldForAlias));
                }

                firstDotPosition = resolvedAlias.indexOf('.');
                secondDotPosition = resolvedAlias.indexOf('.', firstDotPosition + 1);
            }
        }

        return resolvedAlias;
    }

    public Map<String, String> getRegisteredAliases() {
        return registeredAliases.get();
    }

    @Override
    public void close() {
        registeredAliases.set(null);
        aliasCount.set(0);
    }

}
