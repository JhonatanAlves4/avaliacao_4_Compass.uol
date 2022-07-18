package br.com.compassUol.av4.Avaliacao_4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sigla;
    private String ideologia;
    private LocalDate dataFundacao;
    @JsonIgnore
    @OneToMany(mappedBy = "partido")
    private List<Associado> associados = new ArrayList<>();


    public Long getId(Long partidoId) {
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
