package org.example.desafioapitransacao.repository;

import org.example.desafioapitransacao.DTO.Transacao;

import java.util.ArrayList;
import java.util.List;

public class TransacaoRepository {
    private List<Transacao> transacoes = new ArrayList<>();

    public void addTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }
    public List<Transacao> getTransacoes() {
        return new ArrayList<>(transacoes);
    }
    public void deleteTransacoes() {
        transacoes.clear();
    }
}