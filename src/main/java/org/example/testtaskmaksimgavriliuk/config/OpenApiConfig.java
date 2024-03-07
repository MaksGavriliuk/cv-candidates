package org.example.testtaskmaksimgavriliuk.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(
        info = @Info(
                title = "Candidate management service Api",
                description = "Candidate management service", version = "1.0.0",
                contact = @Contact(
                        name = "Gavriliuk Maksim",
                        email = "maks.revival27@gmail.com",
                        url = "https://github.com/MaksGavriliuk"
                )
        )
)
public class OpenApiConfig {
}