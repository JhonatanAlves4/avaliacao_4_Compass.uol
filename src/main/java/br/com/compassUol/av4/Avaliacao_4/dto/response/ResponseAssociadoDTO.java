package br.com.compassUol.av4.Avaliacao_4.dto.response;

import br.com.compassUol.av4.Avaliacao_4.enums.CargoPolitico;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ResponseAssociadoDTO {

    private Long id;
    private String nome;
    private String cargoPolitico;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
    private String sexo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
