package github.mathlazaro.desafioitau.shared.infra.configuration;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi openApi() {
        return GroupedOpenApi.builder()
                .addOpenApiCustomizer(openApi -> openApi.setInfo(getInfo()))
                .group("api")
                .pathsToMatch("/**")
                .build();
    }

    private static Info getInfo() {
        return new Info()
                .title("Desafio Itau - API")
                .description("API para o desafio proposto pelo Itau. Estudo sobre clean architecture e boas práticas.")
                .version("1.0.0")
                .contact(getContact());
    }

    private static Contact getContact() {
        return new Contact()
                .name("Matheus Lázaro de Lima")
                .email("matheus.lazaro@outlook.com")
                .url("https://github.com/mathLazaro");
    }
}
