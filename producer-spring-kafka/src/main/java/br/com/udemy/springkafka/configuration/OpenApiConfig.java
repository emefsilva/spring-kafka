package br.com.udemy.springkafka.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Spring Kafka")
                                .version("1.0")
                                .description("Spring Kafka")
                                .contact(
                                        new Contact()
                                                .name("Emerson")
                                                .email("emerson@email.com")
                                )
                );
    }
}
