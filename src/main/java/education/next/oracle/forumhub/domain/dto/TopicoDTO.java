package education.next.oracle.forumhub.domain.dto;

import education.next.oracle.forumhub.domain.model.Topico;
import jakarta.validation.constraints.NotBlank;

public record TopicoDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {
    public TopicoDTO(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getAutor(),
                topico.getCurso()
        );
    }
}
