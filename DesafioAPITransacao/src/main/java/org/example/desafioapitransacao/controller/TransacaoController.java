package org.example.desafioapitransacao.controller;

import jakarta.validation.Valid;
import org.example.desafioapitransacao.DTO.Estatistica;
import org.example.desafioapitransacao.DTO.Transacao;
import org.example.desafioapitransacao.service.TransacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    private static final Logger logger = LoggerFactory.getLogger(TransacaoController.class);
    @Autowired
    private TransacaoService transacaoService;
    @PostMapping
    public ResponseEntity receberTransacao(@Valid @RequestBody Transacao transacao) {
        try {
            logger.info("Recebendo uma transação, {}", transacao);
            transacaoService.receberTransacao(transacao);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            if(e.getMessage().equals("O valor da transacao nao pode ser negativo.") ||
                e.getMessage().equals("A data da transacao nao pode estar em um momento futuro.") ||
                    e.getMessage().equals("O valor da transacao nao pode ser negativo e a data da transacao nao pode estar em um momento futuro.")
            ){
                logger.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            }else{
                logger.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
    }

    @GetMapping
    public ResponseEntity<List<Transacao>> Transacao(){
        try{
            logger.info("Listando todas as transaçoes");
            return ResponseEntity.ok(transacaoService.getTransacao());
        }catch(IllegalArgumentException e){
            logger.error(e.getMessage());
             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/estatistica")
    public ResponseEntity<Estatistica> getEstatistica(@RequestParam(defaultValue = "60")int tempo){
        try{
            logger.info("Exibindo estatisticas de transacao.");
            return ResponseEntity.ok(transacaoService.getEstatistica(tempo));
        }catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity removerTransacao(){
        try{
            logger.info("Removendo todas as transacoes.");
            transacaoService.deletarTransacao();
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
