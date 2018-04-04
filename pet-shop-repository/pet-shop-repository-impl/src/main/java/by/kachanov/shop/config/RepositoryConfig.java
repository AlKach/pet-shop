package by.kachanov.shop.config;

import javax.sql.DataSource;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("by.kachanov.shop.repository")
public class RepositoryConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.jpa")
    public JpaProperties jparoperties() {
        return new JpaProperties();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource, JpaProperties jpaProperties) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("by.kachanov.shop.dto");
        Properties hibernateProperties = new Properties();
        hibernateProperties.putAll(jpaProperties.getProperties());
        factoryBean.setHibernateProperties(hibernateProperties);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

}
