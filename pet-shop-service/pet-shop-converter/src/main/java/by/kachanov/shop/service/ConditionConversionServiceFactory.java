package by.kachanov.shop.service;

import by.kachanov.shop.service.converter.ConversionContext;
import org.springframework.core.convert.ConversionService;

public interface ConditionConversionServiceFactory {

    ConversionService createConverter(ConversionContext conversionContext);

}
