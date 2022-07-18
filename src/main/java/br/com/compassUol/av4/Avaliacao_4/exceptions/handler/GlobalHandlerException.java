package br.com.compassUol.av4.Avaliacao_4.exceptions.handler;


import br.com.compassUol.av4.Avaliacao_4.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    private static final String ERRO_INTERNO_NO_SERVIDOR = "Erro interno no servidor";
    private static final String PARTIDO_NAO_ENCONTRADO = "Partido não encontrado";
    private static final String ASSOCIADO_NAO_ENCONTRADO = "Associado não encontrado";
    private static final String CARGO_POLITICO_INVALIDO = "Cargo Político não permitido.";
    private static final String IDEOLOGIA_INVALIDA = "Erro ao cadastrar ideologia. Somente será aceito as ideologias: Direita, Esquerda e Centro";
    private static final String SEXO_INVALIDO = "Erro ao cadastrar sexo. Somente será aceito os sexos: Masculino e Feminino";

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ErrorMessage> handlerException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorMessage(ERRO_INTERNO_NO_SERVIDOR));
    }

    @ExceptionHandler(value = {Partido404Exception.class})
    protected ResponseEntity<ErrorMessage> handlePartidoNotFound(Partido404Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(PARTIDO_NAO_ENCONTRADO));
    }

    @ExceptionHandler(value = {Associado404Exception.class})
    protected ResponseEntity<ErrorMessage> handleAssociadoNotFound(Associado404Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(ASSOCIADO_NAO_ENCONTRADO));
    }

    @ExceptionHandler(value = {CargoPoliticoException.class})
    protected ResponseEntity<ErrorMessage> handleCargoInvalid(CargoPoliticoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(CARGO_POLITICO_INVALIDO));
    }

    @ExceptionHandler(value = {IdeologiaException.class})
    protected ResponseEntity<ErrorMessage> handleIdeologiaInvalid(IdeologiaException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(IDEOLOGIA_INVALIDA));
    }

    @ExceptionHandler(value = {SexoException.class})
    protected ResponseEntity<ErrorMessage> handleSexoInvalid(SexoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(SEXO_INVALIDO));
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream().map(fieldError ->
                "Campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage()
        ).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(validationList));
    }

}
