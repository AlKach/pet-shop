package by.kachanov.shop.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("by.kachanov.shop.repository")
@EntityScan("by.kachanov.shop.dto")
@EnableJpaRepositories("by.kachanov.shop.repository")
@EnableTransactionManagement
public class RepositoryConfig {
}
