package by.kachanov.shop;

import by.kachanov.shop.service.ConditionConversionServiceTest;
import by.kachanov.shop.service.converter.AbstractConditionConverterTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ConditionConversionServiceTest.class,
        AbstractConditionConverterTest.class
})
public class Tests {
}
