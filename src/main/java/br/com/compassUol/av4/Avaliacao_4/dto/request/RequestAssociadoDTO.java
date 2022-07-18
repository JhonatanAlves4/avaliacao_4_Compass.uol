package br.com.compassUol.av4.Avaliacao_4.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class RequestAssociadoDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String cargoPolitico;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
    @NotBlank
    private String sexo;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargoPolitico() {
        return cargoPolitico;
    }

    public void setCargoPolitico(String cargoPolitico) {
        this.cargoPolitico = cargoPolitico;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
