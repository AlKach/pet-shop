package by.kachanov.shop.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("by.kachanov.shop.dto")
@SpringBootTest(classes = {
        RSQLServiceConfig.class
})
@ComponentScan("by.kachanov.shop.config")
@EnableTransactionManagement
@ActiveProfiles("test")
public class TestConfig {
}
