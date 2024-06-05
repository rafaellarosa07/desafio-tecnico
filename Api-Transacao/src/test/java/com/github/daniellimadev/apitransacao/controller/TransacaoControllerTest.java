package com.github.daniellimadev.apitransacao.controller;

import com.github.daniellimadev.apitransacao.dto.TransacaoDTO;
import com.github.daniellimadev.apitransacao.service.TransacaoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class TransacaoControllerTest {

    @InjectMocks
    private TransacaoController transacaoController;

    @Mock
    private TransacaoService transacaoService;

    private MockMvc mockMvc;

    TransacaoDTO transacaoDTO;

    @BeforeEach
    public void setup() {

        mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        TransacaoDTO transacaoDTO = new TransacaoDTO("100.00", formatter.format(offsetDateTime));
    }

    @Test
    public void salvarTest() throws Exception {

        assertEquals(HttpStatus.CREATED, transacaoController.salvar(transacaoDTO).getStatusCode());
        log.info("Transação cadastrada com sucesso!");
    }

    @Test
    public void deletarTest() throws Exception {

        transacaoController.salvar(transacaoDTO);

        assertEquals(HttpStatus.OK, transacaoController.deletar().getStatusCode());
        log.info("Lista deletada por completa com sucesso!");

    }

    @Test
    public void buscarTudoTest() throws Exception {

        transacaoController.salvar(transacaoDTO);

        assertEquals(HttpStatus.OK, transacaoController.findAll().getStatusCode());
        log.info("Estatisticas listada com sucesso! ");
    }

}

