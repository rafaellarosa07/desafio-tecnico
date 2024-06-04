package br.com.teamcubation.challenge.controller;
import br.com.teamcubation.challenge.service.EstatisticaService;
import br.com.teamcubation.challenge.service.TransacaoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final TransacaoService transacaoService;
    private final EstatisticaService estatisticaService;

    public EstatisticaController(TransacaoService transacaoService, EstatisticaService estatisticaService) {
        this.transacaoService = transacaoService;
        this.estatisticaService = estatisticaService;
    }

}
