package br.com.teamcubation.challenge.service;
import br.com.teamcubation.challenge.model.Estatistica;
import br.com.teamcubation.challenge.model.Transacao;
import org.springframework.stereotype.Service;
import java.util.DoubleSummaryStatistics;
import java.util.List;


@Service
public class EstatisticaService {

    public Estatistica calcularEstatisticas(List<Transacao> transacoes) {
        DoubleSummaryStatistics stats = transacoes.stream()
                .mapToDouble(Transacao::getValor)
                .summaryStatistics();

        Estatistica estatistica = new Estatistica();
        estatistica.setCount(stats.getCount());
        estatistica.setSum(stats.getSum());
        estatistica.setAvg(stats.getAverage());
        estatistica.setMin(stats.getCount() == 0 ? 0.0 : stats.getMin());
        estatistica.setMax(stats.getCount() == 0 ? 0.0 : stats.getMax());

        return estatistica;
    }

}
