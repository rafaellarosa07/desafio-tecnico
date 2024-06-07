package org.example.desafioapitransacao.DTO;

import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;

public class Transacao {
    @NotNull
    private double valor;
    @NotNull
    private OffsetDateTime dataHora;

    public Transacao(@NotNull double valor, @NotNull OffsetDateTime dataHora) {
        this.valor = valor;
        this.dataHora = dataHora;
    }

    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public OffsetDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(OffsetDateTime dataHora) {
        this.dataHora = dataHora;
    }
}