package org.example.desafioapitransacao.repository;

import org.example.desafioapitransacao.DTO.Transacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransacaoRepositoryTest {
    @Test
    void addTransacao(){
        TransacaoRepository transacaoRepository = new TransacaoRepository();
        Transacao transacao = new Transacao(1000.0, OffsetDateTime.parse("2024-06-07T10:10:25.201-03:00"));

        transacaoRepository.addTransacao(transacao);

        List<Transacao> transacoes = transacaoRepository.getTransacoes();
        List<Transacao> transacaosEsperadas = Arrays.asList(transacao);

        Assertions.assertEquals(transacaosEsperadas, transacoes);
    }
    @Test
    void getTransacoes(){
        TransacaoRepository transacaoRepository = new TransacaoRepository();
        Transacao transacao1 = new Transacao(1000.0, OffsetDateTime.parse("2024-06-07T10:10:25.201-03:00"));
        Transacao transacao2 = new Transacao(2000.0, OffsetDateTime.parse("2024-06-07T10:10:25.201-03:00"));
        transacaoRepository.addTransacao(transacao1);
        transacaoRepository.addTransacao(transacao2);

        List<Transacao> transacoes = transacaoRepository.getTransacoes();
        List<Transacao> expectedTransacoes = Arrays.asList(transacao1, transacao2);
        Assertions.assertEquals(expectedTransacoes, transacoes);
    }
    @Test
    void deleteTransacao(){
        TransacaoRepository transacaoRepository = new TransacaoRepository();
        Transacao transacao1 = new Transacao(1000.0, OffsetDateTime.parse("2024-06-07T10:10:25.201-03:00"));
        Transacao transacao2 = new Transacao(2000.0, OffsetDateTime.parse("2024-06-07T10:10:25.201-03:00"));
        transacaoRepository.addTransacao(transacao1);
        transacaoRepository.addTransacao(transacao2);
        List<Transacao> transacoes = transacaoRepository.getTransacoes();
        Assertions.assertFalse(transacoes.isEmpty());

        transacaoRepository.deleteTransacoes();

        List<Transacao> listaDeTransacoesVazia = transacaoRepository.getTransacoes();
        Assertions.assertTrue(listaDeTransacoesVazia.isEmpty());
    }
}
