package by.kachanov.shop.service.rsql.converters;

import org.springframework.core.convert.converter.Converter;

public interface JpaParameterConverter<T> extends Converter<String, T> {
}
