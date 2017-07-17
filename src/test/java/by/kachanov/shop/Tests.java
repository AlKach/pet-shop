package by.kachanov.shop;

import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;
import org.junit.runner.RunWith;

@RunWith(WildcardPatternSuite.class)
@SuiteClasses({"**/*Test.class", "!**/SpringTest.class", "!**/SpringWebTest.class", "!**/*AbstractEntityCreationTest.class"})
public class Tests {
}
