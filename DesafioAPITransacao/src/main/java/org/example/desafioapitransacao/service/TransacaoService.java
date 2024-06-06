package org.example.desafioapitransacao.service;

import org.example.desafioapitransacao.DTO.Estatistica;
import org.example.desafioapitransacao.DTO.Transacao;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TransacaoService {

    public void receberTransacao(Transacao novaTransacao) {
        if(novaTransacao.getDataHora().isBefore(OffsetDateTime.now()) && novaTransacao.getValor() >= 0) {
            Transacao.addTransacao(novaTransacao);
        }
        else if (novaTransacao.getDataHora().isAfter(OffsetDateTime.now()) || novaTransacao.getValor() < 0) {
            throw new IllegalArgumentException("O valor da transação é negativo ou a data está em um momento futuro.");
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    public List<Transacao> getTransacao() {
        return Transacao.getTransacoes();
    }
    public Estatistica getEstatistica(int tempo){
        System.out.println("Tempo Service: " + tempo);
        return Transacao.getEstatistica(tempo);
    }
    public void deletarTransacao() {
        Transacao.deleteTransacao();
    }
}
