package br.com.teamcubation.challenge.controller;
import br.com.teamcubation.challenge.model.Transacao;
import br.com.teamcubation.challenge.service.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoOperations;

    public TransacaoController(TransacaoService transacaoOperations) {
        this.transacaoOperations = transacaoOperations;
    }

    @PostMapping
    public ResponseEntity<Void> postTransacao(@RequestBody Transacao transacao) {
        if (transacao.getValor() < 0 || transacao.getDataHora().isAfter(OffsetDateTime.now())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        transacaoOperations.addTransacao(transacao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransacoes() {
        transacaoOperations.cleanerTransacoes();
        return ResponseEntity.ok().build();
    }
}


