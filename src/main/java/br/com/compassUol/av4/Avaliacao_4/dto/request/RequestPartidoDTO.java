package br.com.compassUol.av4.Avaliacao_4.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class RequestPartidoDTO {

    @NotBlank
    private String nome;
    @NotBlank
    private String sigla;
    @NotBlank
    private String ideologia;
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataFundacao;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getIdeologia() {
        return ideologia;
    }

    public void setIdeologia(String ideologia) {
        this.ideologia = ideologia;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
}
