package by.kachanov.shop.service.converter;

import org.springframework.core.convert.ConversionService;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConversionContext {

    private final Class<?> rootType;

    private ConversionService conversionService;

    private final Map<String, String> registeredAliases = new LinkedHashMap<>();

    public ConversionContext(Class<?> type) {
        this.rootType = type;
    }

    public Class<?> getRootType() {
        return rootType;
    }

    public ConversionService getConversionService() {
        if (conversionService == null) {
            throw new IllegalStateException("Conversion service is not defined");
        }
        return conversionService;
    }

    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public Map<String, String> getRegisteredAliases() {
        return registeredAliases;
    }
}
