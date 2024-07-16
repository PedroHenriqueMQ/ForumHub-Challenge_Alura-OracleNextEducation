package education.next.oracle.forumhub.domain.service;

import education.next.oracle.forumhub.domain.dto.UsuarioDTO;
import education.next.oracle.forumhub.domain.model.Usuario;
import education.next.oracle.forumhub.domain.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username);
    }

    public UsuarioDTO createUser(UsuarioDTO usuarioDTO) {
        var senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());
        var usuarioParaSalvar = new Usuario(usuarioDTO);
        usuarioParaSalvar.setSenha(senhaCriptografada);

        return new UsuarioDTO(usuarioRepository.save(usuarioParaSalvar));
    }
}
