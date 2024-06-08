package br.com.teamcubation.challenge.controller;
import br.com.teamcubation.challenge.model.Estatistica;
import br.com.teamcubation.challenge.service.EstatisticaService;
import br.com.teamcubation.challenge.service.TransacaoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/estatistica", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstatisticaController {

    private final TransacaoService transacaoOperations;
    private final EstatisticaService estatisticaOperations;

    public EstatisticaController(TransacaoService transacaoOperations, EstatisticaService estatisticaService) {
        this.transacaoOperations = transacaoOperations;
        this.estatisticaOperations = estatisticaService;
    }

    @GetMapping
    public Estatistica getEstatisticas() {
        return estatisticaOperations.calcularEstatisticas(transacaoOperations.obterTransacoes());
    }

}
