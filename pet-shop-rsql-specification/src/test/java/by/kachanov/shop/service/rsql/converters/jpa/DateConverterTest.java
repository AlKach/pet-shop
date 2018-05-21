package by.kachanov.shop.service.rsql.converters.jpa;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import by.kachanov.shop.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestConfig.class)
@ExtendWith(SpringExtension.class)
public class DateConverterTest {
    
    @Autowired
    private DateConverter dateConverter;
    
    @Test
    public void testDateConversion() {
        Date converted = dateConverter.convert("23-Jan-2019");
        Date expected = Date
                .from(LocalDate.of(2019, Month.JANUARY, 23).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        assertEquals(expected, converted);
    }

    @Test
    public void testIllegalStringConversion() {
        assertThrows(IllegalArgumentException.class, () -> dateConverter.convert("23 January 2019"));
    }

}
