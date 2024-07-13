package education.next.oracle.forumhub.controller;

import education.next.oracle.forumhub.domain.dto.TopicoDTO;
import education.next.oracle.forumhub.domain.dto.TopicoDetalhadoDTO;
import education.next.oracle.forumhub.domain.model.Topico;
import education.next.oracle.forumhub.domain.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topicos")
public class TopicoController {
    @Autowired
    TopicoService topicoService;

    @Transactional
    @PostMapping
    public ResponseEntity create (@Valid @RequestBody TopicoDTO topicoDTO, UriComponentsBuilder uriBuilder) {
        topicoService.saveTopico(topicoDTO);

        var topico = new Topico(topicoDTO);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(topicoDTO);
    }

    @GetMapping
    public ResponseEntity<List<TopicoDetalhadoDTO>> readAll () {
        var topicos = topicoService.findAllTopico();

        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TopicoDetalhadoDTO>> readOne (@PathVariable Long id) {
        var topico = topicoService.findById(id);

        return ResponseEntity.ok(topico);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Optional<TopicoDTO>> update (
            @PathVariable Long id, @Valid @RequestBody TopicoDTO topicoDTO
    ) {
        var topico = topicoService.updateTopico(id, topicoDTO);

        return ResponseEntity.ok(topico);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable Long id) {
        topicoService.deleteTopico(id);

        return ResponseEntity.noContent().build();
    }
}
