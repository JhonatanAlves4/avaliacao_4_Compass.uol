package br.com.compassUol.av4.Avaliacao_4.dto.response;

import br.com.compassUol.av4.Avaliacao_4.enums.Ideologia;
import br.com.compassUol.av4.Avaliacao_4.model.Associado;
import br.com.compassUol.av4.Avaliacao_4.model.Partido;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ResponsePartidoDTO {

    private Long id;
    private String nome;
    private String sigla;
    private String ideologia;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataFundacao;
    private List<Associado> associados;


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

    public List<Associado> getAssociados() {
        return associados;
    }

    public void setAssociados(List<Associado> associados) {
        this.associados = associados;
    }
}
