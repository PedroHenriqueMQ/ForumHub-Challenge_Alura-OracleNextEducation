package education.next.oracle.forumhub.domain.repository;

import education.next.oracle.forumhub.domain.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensagem(String titulo, String mensagem);
    List<Topico> findByEstado(char estado);
}
