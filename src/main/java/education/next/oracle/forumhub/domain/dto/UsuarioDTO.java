package education.next.oracle.forumhub.domain.dto;

import jakarta.validation.constraints.Email;

public record UsuarioDTO (@Email String email, String senha) {
}
