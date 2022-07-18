package br.com.compassUol.av4.Avaliacao_4.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cargoPolitico;
    private LocalDate dataNascimento;
    private String sexo;
    @ManyToOne
    @JoinColumn(name = "partido_id", referencedColumnName = "id")
    private Partido partido;

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

    public Partido getPartido(Partido partido) {
        return this.partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }
}
