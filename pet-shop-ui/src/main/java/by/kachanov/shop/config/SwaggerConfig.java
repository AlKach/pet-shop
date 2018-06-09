package by.kachanov.shop.config;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class SwaggerConfig {

    @Bean
    public Docket apiDocConfig(@Value("${springfox.documentation.swagger.v2.pathMapping}") String pathMapping,
                               ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("by.kachanov.shop"))
                    .build()
                .apiInfo(apiInfo)
                .pathMapping(pathMapping);
    }

    @Bean
    public ApiInfo apiInfo(@Value("${springfox.documentation.swagger.v2.title}") String title,
                           @Value("${springfox.documentation.swagger.v2.description}") String description,
                           @Value("${springfox.documentation.swagger.v2.version}") String version,
                           @Value("${springfox.documentation.swagger.v2.termsOfServiceUrl}") String termsOfServiceUrl,
                           @Value("${springfox.documentation.swagger.v2.license}") String license,
                           @Value("${springfox.documentation.swagger.v2.licenseUrl}") String licenseUrl,
                           Contact contact,
                           List<VendorExtension> vendorExtensions) {
        return new ApiInfo(title, description, version, termsOfServiceUrl, contact, license, licenseUrl, vendorExtensions);
    }

    @Bean
    public Contact contact(@Value("${springfox.documentation.swagger.v2.contact.name}") String name,
                           @Value("${springfox.documentation.swagger.v2.contact.url}") String url,
                           @Value("${springfox.documentation.swagger.v2.contact.email}") String email) {
        return new Contact(name, url, email);
    }

    @Bean
    public List<VendorExtension> vendorExtensions() {
        return Collections.emptyList();
    }

}
