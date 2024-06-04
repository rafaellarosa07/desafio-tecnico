package com.github.daniellimadev.apitransacao.service;

import com.github.daniellimadev.apitransacao.dto.TransacaoDTO;
import com.github.daniellimadev.apitransacao.model.Transacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TransacaoService {
    private static List<Transacao> transacoes =  new ArrayList<>();

    public Transacao salvar(TransacaoDTO transacaoDTO) {

        Transacao transacao = new Transacao(Double.parseDouble(transacaoDTO.getValor()), OffsetDateTime.parse(transacaoDTO.getDataHora()));
        transacoes.add(transacao);

        log.info("Transação cadastrada com sucesso!");
        return transacao;
    }
}
