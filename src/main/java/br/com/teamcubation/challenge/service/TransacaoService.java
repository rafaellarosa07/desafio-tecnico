package br.com.teamcubation.challenge.service;
import br.com.teamcubation.challenge.model.Transacao;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class TransacaoService {

    private List<Transacao> transacoes = new LinkedList<>();

    public void addTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public void cleanerTransacoes() {
        transacoes.clear();
    }

    public List<Transacao> obterTransacoes() {
        OffsetDateTime agora = OffsetDateTime.now();
        return transacoes.stream()
                .filter(t -> t.getDataHora().isAfter(agora.minusSeconds(60)))
                .toList();
    }
}
