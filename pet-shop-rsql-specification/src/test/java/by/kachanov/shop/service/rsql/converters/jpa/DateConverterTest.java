package by.kachanov.shop.service.rsql.converters.jpa;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;

import by.kachanov.shop.config.TestConfig;
import by.kachanov.shop.service.rsql.converters.jpa.DateConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
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

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalStringConversion() {
        dateConverter.convert("23 January 2019");
    }

}
