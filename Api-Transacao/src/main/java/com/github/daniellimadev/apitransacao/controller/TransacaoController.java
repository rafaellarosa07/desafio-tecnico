package com.github.daniellimadev.apitransacao.controller;

import com.github.daniellimadev.apitransacao.dto.EstatisticasDTO;
import com.github.daniellimadev.apitransacao.dto.TransacaoDTO;
import com.github.daniellimadev.apitransacao.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "api-de-estatiticas-de-transacao")
@RequestMapping(value = "/transacao", produces = {"application/json"})
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Operation(summary = "Cria uma transação", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação criada com sucesso!"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida!"),
            @ApiResponse(responseCode = "500", description = "Serviço indisponível!"),
    })
    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid TransacaoDTO transacaoDTO) {
        transacaoService.salvar(transacaoDTO);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Mostra as estatísticas das transações cadastradas", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transações deletadas com sucesso!"),
            @ApiResponse(responseCode = "500", description = "Serviço indisponível!"),
    })
    @GetMapping ("/estatistica")
    public ResponseEntity<EstatisticasDTO> findAll() {

        return ResponseEntity.ok(transacaoService.estatisticas());
    }

    @Operation(summary = "deleta todas as transações", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transações deletadas com sucesso!"),
            @ApiResponse(responseCode = "500", description = "Serviço indisponível!"),
    })
    @DeleteMapping
    public ResponseEntity<Void> deletar() {
        transacaoService.deletar();
        return ResponseEntity.status(200).build();
    }
}

