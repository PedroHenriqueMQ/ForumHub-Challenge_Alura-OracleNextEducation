package education.next.oracle.forumhub.infra.exception;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.sql.DataTruncation;

@RestControllerAdvice
public class ExceptionListener {
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity tratarErro409 (EntityExistsException exception) {
        return ResponseEntity.status(409).body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro422MethodArgument (MethodArgumentNotValidException exception) {
        var mensagensErros = exception.getFieldErrors().stream()
                .map(fieldError -> "%s %s".formatted(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        return ResponseEntity.status(422).body(mensagensErros);
    }

    @ExceptionHandler(DataTruncation.class)
    public ResponseEntity tratarErro422DataTruncation (DataTruncation ex) {
        var mensagemErro = "Erro no campo %s.".formatted(
                ex.getMessage().replace("Data truncation: ", "").split("'")[1]
        );

        return ResponseEntity.status(422).body(mensagemErro);
    }

    @ExceptionHandler(JWTCreationException.class)
    public ResponseEntity tratarErro500CreateToken (JWTCreationException ex) {
        return ResponseEntity.status(500).body("Erro na geração de token: " + ex.getMessage());
    }

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity tratarErro401InvalidToken (JWTVerificationException ex) {
        return ResponseEntity.status(401).body(ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity tratarErro404InvalidUsername (UsernameNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
