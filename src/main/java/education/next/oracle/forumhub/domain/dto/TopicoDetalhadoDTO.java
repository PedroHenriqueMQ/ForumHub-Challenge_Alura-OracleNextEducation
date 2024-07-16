package education.next.oracle.forumhub.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import education.next.oracle.forumhub.domain.model.Topico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoDetalhadoDTO (
    @NotBlank
    String titulo,
    @NotBlank
    String mensagem,
    @NotNull @JsonAlias("data_criacao")
    LocalDateTime dataCriacao,
    @NotBlank
    char estado,
    @NotBlank
    String autor,
    @NotBlank
    String curso
    ) {
    public TopicoDetalhadoDTO(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getEstado(),
                topico.getAutor(),
                topico.getCurso()
        );
    }
}
