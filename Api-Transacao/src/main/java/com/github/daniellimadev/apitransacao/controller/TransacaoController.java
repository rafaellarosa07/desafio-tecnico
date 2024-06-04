package com.github.daniellimadev.apitransacao.controller;

import com.github.daniellimadev.apitransacao.dto.EstatisticasDTO;
import com.github.daniellimadev.apitransacao.dto.TransacaoDTO;
import com.github.daniellimadev.apitransacao.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;


    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody TransacaoDTO transacaoDTO) {
        transacaoService.salvar(transacaoDTO);
        return ResponseEntity.status(201).build();
    }

    @GetMapping ("/estatistica")
    public ResponseEntity<EstatisticasDTO> findAll() {

        return ResponseEntity.ok(transacaoService.estatisticas());
    }
}

