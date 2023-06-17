package com.giangnxt.student_api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Student Enrollment API")
                        .description("This is a demo Application built using Spring to store and retrieve student records")
                        .version("0.1")
                        .termsOfService("Terms of Service")
                        .contact(new Contact().name("GiangNXT").email("giang.nxt.se@gmail.com"))
                );
    }

}
