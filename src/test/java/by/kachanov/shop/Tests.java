package by.kachanov.shop;

import by.kachanov.shop.repository.AbstractRepositoryTest;
import by.kachanov.shop.service.ConditionConversionServiceTest;
import by.kachanov.shop.service.converter.AbstractConditionConverterTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ConditionConversionServiceTest.class,
        AbstractConditionConverterTest.class,
        AbstractRepositoryTest.class
})
public class Tests {
}
