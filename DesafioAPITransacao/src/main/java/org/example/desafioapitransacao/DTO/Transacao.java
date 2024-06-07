package org.example.desafioapitransacao.DTO;

import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.*;

public class Transacao {
    @NotNull
    private double valor;
    @NotNull
    private OffsetDateTime dataHora;
    private static List<Transacao> transacoes = new ArrayList<>();

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
    public static void addTransacao(Transacao transacao) {transacoes.add(transacao);}
    public static List<Transacao> getTransacoes() {return transacoes;}
    public static void deleteTransacao(){transacoes.clear();}
}