package br.com.compassUol.av4.Avaliacao_4.enums;

public enum Sexo {
    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private String value;

    public String getValue() {
        return value;
    }

    Sexo(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
