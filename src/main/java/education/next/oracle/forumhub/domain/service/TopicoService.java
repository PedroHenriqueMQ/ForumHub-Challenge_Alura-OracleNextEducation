package education.next.oracle.forumhub.domain.service;

import education.next.oracle.forumhub.domain.dto.TopicoDTO;
import education.next.oracle.forumhub.domain.dto.TopicoDetalhadoDTO;
import education.next.oracle.forumhub.domain.model.Topico;
import education.next.oracle.forumhub.domain.repository.ITopicoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    ITopicoRepository topicoRepository;

    public void saveTopico(TopicoDTO topicoDTO) {
        var topicoDuplicado = topicoRepository.existsByTituloAndMensagem(topicoDTO.titulo(), topicoDTO.mensagem());
        if (topicoDuplicado) throw new EntityExistsException("O tópico que você está tentando salvar já existe.");

        var topico = new Topico(topicoDTO);
        topicoRepository.save(topico);
    }

    public List<TopicoDetalhadoDTO> findAllTopico () {
        return topicoRepository.findByEstado('A').stream().map(TopicoDetalhadoDTO::new).toList();
    }

    public Optional<TopicoDetalhadoDTO> findById (Long id) {
        var consulta = topicoRepository.findById(id);

        if (consulta.isPresent() && consulta.get().getEstado() == 'A')
            return Optional.of(new TopicoDetalhadoDTO(consulta.get()));
        else return Optional.empty();
    }

    public Optional<TopicoDTO> updateTopico(Long id, @Valid TopicoDTO topicoDTO) {
        var consulta = topicoRepository.findById(id);
        var atualizacaoInvalida =
                topicoRepository.existsByTituloAndMensagem(topicoDTO.titulo(), topicoDTO.mensagem());

        if (atualizacaoInvalida)
            throw new EntityExistsException("O tópico que você está tentando atualizar já existe.");
        else if (consulta.isPresent() && consulta.get().getEstado() == 'A')
            return Optional.of(consulta.get().atualizarInformacoes(topicoDTO));
        else
            return Optional.empty();
    }

    public void deleteTopico (Long id) {
        var consulta = topicoRepository.findById(id);
        //Exclusão lógica
        consulta.ifPresent(Topico::desativarTopico);
    }
}
