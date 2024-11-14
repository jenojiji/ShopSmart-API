package com.personal.shop_smart.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI retailpulseOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ShopSmart API")
                        .description("Documentation ShopSmart API")
                        .version("1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0"))
                        .contact(new Contact()
                                .name("ShopSmart API Admin")
                                .url("http://retailpulse.com")
                                .email("contact.retailpulse@gmail.com")))
                .addServersItem(new Server().url("/"));
    }
}
