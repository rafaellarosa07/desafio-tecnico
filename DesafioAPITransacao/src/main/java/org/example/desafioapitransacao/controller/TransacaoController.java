package org.example.desafioapitransacao.controller;

import jakarta.validation.Valid;
import org.example.desafioapitransacao.DTO.Estatistica;
import org.example.desafioapitransacao.DTO.Transacao;
import org.example.desafioapitransacao.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
    @Autowired
    private TransacaoService transacaoService;
    @PostMapping
    public ResponseEntity<String> receberTransacao(@Valid @RequestBody Transacao transacao) {
        try {
            transacaoService.receberTransacao(transacao);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            if(e.getMessage().equals("O valor da transação é negativo ou a data está em um momento futuro.")){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
    }
    @GetMapping
    public List<Transacao> getTransacao(){
        return transacaoService.getTransacao();
    }
    @GetMapping("/estatistica")
    public Estatistica getEstatistica(){
        return transacaoService.getEstatistica();
    }
    @DeleteMapping("/delete")
    public void removerTransacao(){
        transacaoService.deletarTransacao();
    }

}
