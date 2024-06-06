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
    public OffsetDateTime getDataTransacao() {
        return dataHora;
    }
    public void setDataTransacao(OffsetDateTime dataTransacao) {
        this.dataHora = dataTransacao;
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
    public static Estatistica getEstatistica() {

        OffsetDateTime dataHoraAtual = OffsetDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        List<Transacao> transacoesDosUltimos60segundos = transacoes.stream()
                .filter(ultimasTransacoes -> ultimasTransacoes.getDataTransacao()
                        .isAfter(dataHoraAtual.minusSeconds(60)))
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

