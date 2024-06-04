package com.github.daniellimadev.apitransacao.model;

import java.time.OffsetDateTime;

public class Transacao {

    private double valor;
    private OffsetDateTime dataHora;

    public Transacao(double valor, OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public double getValor() {
        return valor;
    }

    public OffsetDateTime getDataHora() {
        return dataHora;
    }

}
