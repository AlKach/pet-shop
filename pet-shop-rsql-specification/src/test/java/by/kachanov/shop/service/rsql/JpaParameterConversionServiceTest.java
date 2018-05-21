package by.kachanov.shop.service.rsql;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Currency;
import java.util.Date;

import by.kachanov.shop.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestConfig.class)
@ExtendWith(SpringExtension.class)
public class JpaParameterConversionServiceTest {

    @Autowired
    private JpaParameterConversionService jpaParameterConversionService;

    @Test
    public void testStandardConverter() {
        Currency converted = jpaParameterConversionService.convert("USD", Currency.class);

        assertEquals(Currency.getInstance("USD"), converted);
    }

    @Test
    public void testCustomDateConverter() {
        Date converted = jpaParameterConversionService.convert("17-Jun-2007", Date.class);
        Date expected =
                Date.from(LocalDate.of(2007, Month.JUNE, 17).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        assertEquals(expected, converted);
    }

    @Test
    public void testInvalidDateConversion() {
        assertThrows(
                ConversionFailedException.class,
                () -> jpaParameterConversionService.convert("17 June 2007", Date.class));
    }

}
