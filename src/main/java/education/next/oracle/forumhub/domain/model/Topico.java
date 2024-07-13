package education.next.oracle.forumhub.domain.model;

import education.next.oracle.forumhub.domain.dto.TopicoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Table(name = "topicos")
@Entity(name = "Topico")
@NoArgsConstructor
public class Topico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    private char estado;
    private String autor;
    private String curso;

    public Topico(TopicoDTO topicoDTO) {
        this.id = null;
        this.titulo = topicoDTO.titulo();
        this.mensagem = topicoDTO.mensagem();
        this.dataCriacao = LocalDateTime.now();
        this.estado = 'A';
        this.autor = topicoDTO.autor();
        this.curso = topicoDTO.curso();
    }

    public TopicoDTO atualizarInformacoes(TopicoDTO topicoDTO) {
        this.titulo = topicoDTO.titulo();
        this.mensagem = topicoDTO.mensagem();
        this.autor = topicoDTO.autor();
        this.curso = topicoDTO.curso();

        return new TopicoDTO(this);
    }

    public void desativarTopico () {
        this.estado = 'D';
    }
}
