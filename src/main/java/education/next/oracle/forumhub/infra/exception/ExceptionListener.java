package education.next.oracle.forumhub.infra.exception;

import jakarta.persistence.EntityExistsException;
import org.springframework.http.ResponseEntity;
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
}
