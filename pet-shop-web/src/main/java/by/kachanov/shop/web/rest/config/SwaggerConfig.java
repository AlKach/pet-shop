package by.kachanov.shop.web.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:config.properties")
public class SwaggerConfig {

    @Value("${springfox.documentation.swagger.v2.pathMapping}")
    private String pathMapping;

    @Value("${springfox.documentation.swagger.v2.title}")
    private String title;

    @Value("${springfox.documentation.swagger.v2.description}")
    private String description;

    @Value("${springfox.documentation.swagger.v2.version}")
    private String version;

    @Value("${springfox.documentation.swagger.v2.termsOfServiceUrl}")
    private String termsOfServiceUrl;

    @Value("${springfox.documentation.swagger.v2.contact}")
    private String contact;

    @Value("${springfox.documentation.swagger.v2.license}")
    private String license;

    @Value("${springfox.documentation.swagger.v2.licenseUrl}")
    private String licenseUrl;

    @Bean
    public Docket apiDocConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping(pathMapping);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(title, description, version, termsOfServiceUrl, contact, license, licenseUrl);
    }

}
