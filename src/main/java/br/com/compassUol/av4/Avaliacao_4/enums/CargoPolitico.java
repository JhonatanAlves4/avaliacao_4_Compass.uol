package br.com.compassUol.av4.Avaliacao_4.enums;

public enum CargoPolitico {
    VEREADOR("Vereador"),
    PREFEITO("Prefeito"),
    DEPUTADO_ESTADUAL("Deputado Estadual"),
    DEPUTADO_FEDERAL("Deputado Federal"),
    SENADOR("Senador"),
    GOVERNADOR("Governador"),
    NENHUM("Nenhum");

    private String value;

    public String getValue() {
        return value;
    }

    CargoPolitico(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
