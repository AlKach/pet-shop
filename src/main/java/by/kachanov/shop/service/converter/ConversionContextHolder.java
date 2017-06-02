package by.kachanov.shop.service.converter;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConversionContextHolder {

    private ThreadLocal<Class<?>> currentType = new ThreadLocal<>();

    private ThreadLocal<Map<String, String>> registeredAliases = new ThreadLocal<>();

    private ThreadLocal<Integer> aliasCount = new ThreadLocal<>();

    private ConversionContextHolder() {

    }

    private static class ConversionContextHolderInternal {
        private static final ConversionContextHolder INSTANCE = new ConversionContextHolder();
    }

    public static ConversionContextHolder getInstance() {
        return ConversionContextHolderInternal.INSTANCE;
    }

    public void setCurrentType(Class<?> currentType) {
        this.currentType.set(currentType);
    }

    public Class<?> getCurrentType() {
        return this.currentType.get();
    }

    public String getAlias(String field) {
        Map<String, String> aliases = registeredAliases.get();
        if (aliases == null) {
            aliases = new LinkedHashMap<>();
            registeredAliases.set(aliases);
            aliasCount.set(0);
        }

        if (aliases.containsKey(field)) {
            return aliases.get(field);
        }

        if (field.contains(".")) {
            int firstDotPosition = 0;
            int secondDotPosition = field.indexOf(".");
            while (secondDotPosition != -1) {
                String fieldForAlias = field.substring(0, secondDotPosition);
                if (!aliases.containsKey(fieldForAlias)) {
                    Integer count = aliasCount.get();
                    aliasCount.set(count + 1);
                    String alias = "alias" + count;
                    aliases.put(fieldForAlias, alias);
                    field = alias + field.substring(secondDotPosition);
                }

                firstDotPosition = field.indexOf(".");
                secondDotPosition = field.indexOf(".", firstDotPosition + 1);
            }
        }


        return field;
    }

    public Map<String, String> getRegisteredAliases() {
        return registeredAliases.get();
    }

}
