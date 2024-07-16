package education.next.oracle.forumhub.domain.dto;

import education.next.oracle.forumhub.domain.model.Usuario;
import jakarta.validation.constraints.Email;

public record UsuarioDTO (@Email String email, String senha) {
    public UsuarioDTO(Usuario usuario) {
        this(usuario.getUsername(), usuario.getPassword());
    }
}
