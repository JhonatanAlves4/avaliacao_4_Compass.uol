package br.com.compassUol.av4.Avaliacao_4.exceptions.handler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {

    private String message;

    private List<String> validationsErrors;

    public ErrorMessage(String message) {
        this.message = message;
    }

    public ErrorMessage(List<String> validationsErrors) {
        this.validationsErrors = validationsErrors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getValidationsErrors() {
        return validationsErrors;
    }

    public void setValidationsErrors(List<String> validationsErrors) {
        this.validationsErrors = validationsErrors;
    }
}
