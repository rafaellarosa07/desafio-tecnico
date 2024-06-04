package com.github.daniellimadev.apitransacao.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransacaoDTO {

    @NotNull
    @NotBlank
    private String valor;

    @NotNull
    @NotBlank
    private String dataHora;

    public TransacaoDTO(String valor, String dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public String getValor() {
        return valor;
    }

    public String getDataHora() {
        return dataHora;
    }
}
