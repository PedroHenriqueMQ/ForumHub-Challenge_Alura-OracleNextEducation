package education.next.oracle.forumhub.controller;

import education.next.oracle.forumhub.domain.dto.UsuarioDTO;
import education.next.oracle.forumhub.domain.model.Usuario;
import education.next.oracle.forumhub.infra.security.TokenDTO;
import education.next.oracle.forumhub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class UsuarioController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> efetuarLogin(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(usuarioDTO.email(), usuarioDTO.senha());
        Authentication authentication = manager.authenticate(authenticationToken);

        if (!authentication.isAuthenticated()) ResponseEntity.unprocessableEntity().body(usuarioDTO);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(tokenJWT));
    }
}
