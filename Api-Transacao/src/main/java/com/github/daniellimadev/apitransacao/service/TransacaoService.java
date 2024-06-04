package com.github.daniellimadev.apitransacao.service;

import com.github.daniellimadev.apitransacao.dto.EstatisticasDTO;
import com.github.daniellimadev.apitransacao.dto.TransacaoDTO;
import com.github.daniellimadev.apitransacao.model.Transacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
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

    public EstatisticasDTO estatisticas() {

        List<Double> estatisticas = new ArrayList<>();
        OffsetDateTime dataAtual = OffsetDateTime.now();

        Collections.reverse(transacoes);
        for (Transacao transacao : transacoes) {

            var duration = Duration.between(dataAtual, transacao.getDataHora());
            if (duration.getSeconds() <= 60) {
                estatisticas.add(transacao.getValor());
            }
        }

        EstatisticasDTO estatísticasDTO = new EstatisticasDTO();

        estatísticasDTO.setCount(estatisticas.size());

        estatísticasDTO.setAvg(estatisticas.stream()
                .mapToDouble(d -> d)
                .average()
                .orElse(0));

        estatísticasDTO.setSum(estatisticas.stream()
                .mapToDouble(d -> d)
                .sum());

        estatísticasDTO.setMin(estatisticas.stream()
                .mapToDouble(d -> d)
                .min()
                .orElse(0.00));

        estatísticasDTO.setMax(estatisticas.stream()
                .mapToDouble(d -> d)
                .max()
                .orElse(0.00));

        log.info("Estatisticas listada com sucesso!");

        return estatísticasDTO;
    }
}
