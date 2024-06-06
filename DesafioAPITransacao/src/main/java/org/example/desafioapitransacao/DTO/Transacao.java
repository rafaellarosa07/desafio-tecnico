package org.example.desafioapitransacao.DTO;

import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

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
    public static void addTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }
    public static List<Transacao> getTransacoes() {
        return new ArrayList<>(transacoes);
    }
    public static void deleteTransacao(){
        transacoes.clear();
    }
    public static Estatistica getEstatistica(int tempo) {
        System.out.println("Tempo Entidade: " + tempo);
        OffsetDateTime dataHoraAtual = OffsetDateTime.now();
        System.out.println(dataHoraAtual);
        List<Transacao> transacoesDosUltimos60segundos = transacoes.stream()
                .filter(ultimasTransacoes -> ultimasTransacoes.getDataHora()
                        .isAfter(dataHoraAtual.minusSeconds(tempo)))
                .collect(Collectors.toUnmodifiableList());

        int qtdTransacoes = transacoesDosUltimos60segundos.size();

        if(qtdTransacoes == 0){
            throw new IllegalArgumentException("A lista de transações está vazia.");
        }
        DoubleSummaryStatistics estatisticasDaTransacao = transacoesDosUltimos60segundos.stream()
                .mapToDouble(Transacao::getValor).summaryStatistics();

        return new Estatistica(
                qtdTransacoes,
                estatisticasDaTransacao.getSum(),
                estatisticasDaTransacao.getAverage(),
                estatisticasDaTransacao.getMin(),
                estatisticasDaTransacao.getMax());
    }
}

