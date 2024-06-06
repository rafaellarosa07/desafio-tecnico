package org.example.desafioapitransacao.service;

import org.example.desafioapitransacao.DTO.Estatistica;
import org.example.desafioapitransacao.DTO.Transacao;
import org.example.desafioapitransacao.controller.TransacaoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.IllegalFormatFlagsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {
    private static final Logger logger = LoggerFactory.getLogger(TransacaoService.class);
    public void receberTransacao(Transacao novaTransacao) {
        if(novaTransacao.getValor() < 0) {
            logger.info("O valor da transacao nao pode ser negativo.");
            throw new IllegalArgumentException("O valor da transacao nao pode ser negativo.");
        }else if(novaTransacao.getDataHora().isAfter(OffsetDateTime.now())) {
            logger.info("A data da transacao nao pode estar em um momento futuro.");
            throw new IllegalArgumentException("A data da transacao nao pode estar em um momento futuro.");
        }else if(novaTransacao.getValor() < 0 || novaTransacao.getDataHora().isAfter(OffsetDateTime.now())){
            logger.info("O valor da transacao nao pode ser negativo e a data da transacao nao pode estar em um momento futuro.");
            throw new IllegalArgumentException("O valor da transacao nao pode ser negativo e a data da transacao nao pode estar em um momento futuro.");
        }else{
            logger.info("Recebendo dados de transacao mediante valicao de data sendo sempre em momento passado e valor igual ou maior doque zero.");
            Transacao.addTransacao(novaTransacao);
        }
    }
    public List<Transacao> getTransacao() {
        if(!Transacao.getTransacoes().isEmpty()) {
            logger.info("Retornando lista de transacoes");
            return new ArrayList<>(Transacao.getTransacoes());
        }else{
            logger.info("Nao foi possivel retornar transacoes, a lista esta vazia.");
            throw new IllegalFormatFlagsException("A lista esta vazia");
        }
    }
    public Estatistica getEstatistica(int tempo){
        OffsetDateTime dataHoraAtual = OffsetDateTime.now();

        if(getTransacao().isEmpty()){
            throw new IllegalArgumentException("A lista de transacoes esta vazia");
        }
        List<Transacao> transacoesDosUltimos60segundos = getTransacao().stream()
                .filter(ultimasTransacoes -> ultimasTransacoes.getDataHora()
                        .isAfter(dataHoraAtual.minusSeconds(tempo)))
                .collect(Collectors.toUnmodifiableList());

        int qtdTransacoes = transacoesDosUltimos60segundos.size();
        if(qtdTransacoes == 0){
            throw new IllegalArgumentException("Nao ha registro de transacoes para o intervalo de tempo escolhido");
        }else{
            DoubleSummaryStatistics estatisticasDaTransacao = transacoesDosUltimos60segundos.stream()
                    .mapToDouble(Transacao::getValor).summaryStatistics();

            return new Estatistica(
                    qtdTransacoes,
                    estatisticasDaTransacao.getSum(),
                    estatisticasDaTransacao.getAverage(),
                    estatisticasDaTransacao.getMin(),
                    estatisticasDaTransacao.getMax()
            );
        }
    }
    public void deletarTransacao() {
        logger.info("Excluindo registro de transacao");
        logger.info("Estado da lista antes da exclusão: " + Transacao.getTransacoes().size());
        Transacao.deleteTransacao();
        logger.info("Estado da lista antes da exclusão: " + Transacao.getTransacoes().size());
        if(!Transacao.getTransacoes().isEmpty()) {
            logger.info("Houve algum problema ao tentar apagar os itens da lista.");
            throw new IllegalArgumentException("Houve algum problema ao tentar apagar os itens da lista.");
        }
    }
}