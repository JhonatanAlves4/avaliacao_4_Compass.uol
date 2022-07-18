package br.com.compassUol.av4.Avaliacao_4.enums;

public enum Ideologia {
    DIREITA("Direita"),
    CENTRO("Centro"),
    ESQUERDA("Esquerda");

    private String value;

    public String getValue() {
        return value;
    }

    Ideologia(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
