package education.next.oracle.forumhub.domain.repository;

import education.next.oracle.forumhub.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByEmail(String email);
}
