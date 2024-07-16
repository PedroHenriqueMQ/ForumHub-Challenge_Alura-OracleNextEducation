package education.next.oracle.forumhub.infra.springdoc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;


@OpenAPIDefinition(
        info = @Info(
                title = "ForumHub API",
                version = "0.1",
                description = "Simula o fórum da alura, onde os alunos podem fazer publicações sobre quaisquer temas.",
                contact = @Contact(
                        name = "PedroHenriqueMQ",
                        url = "https://github.com/PedroHenriqueMQ/ForumHub-Challenge_Alura-OracleNextEducation"
                )
        )
)
@SecurityScheme(
        name = "Permissão de Acesso",
        description = "Token JWT de autorização",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn. HEADER
)
public class SpringDocConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearer-key"));
    }
}
