package by.kachanov.shop.service.rsql.converters.jpa;

import org.springframework.core.convert.converter.Converter;

public interface JpaParameterConverter<T> extends Converter<String, T> {
}
