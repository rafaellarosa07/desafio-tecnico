package org.example.desafioapitransacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.desafioapitransacao.DTO.Estatistica;
import org.example.desafioapitransacao.DTO.Transacao;
import org.example.desafioapitransacao.service.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TransacaoController.class)
public class TransacaoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransacaoService service;

    Transacao transacao;
    @BeforeEach
    void setUp() {
        transacao = new Transacao(145.00, OffsetDateTime.parse("2024-06-06T20:10:25.201-03:00"));
    }
    @Test
    void receberTransacaoShouldReturnStatusCode201Created() throws Exception {
        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacao)))
                .andExpect(status().isCreated());
        verify(service, times(1)).receberTransacao(Mockito.any(Transacao.class));
    }
    @Test
    void receberTransacaoShouldReturnStatusCode422IfValorTransacaoBeNegativo() throws Exception {
        doThrow(new IllegalArgumentException("O valor da transacao nao pode ser negativo."))
                .when(service).receberTransacao(any(Transacao.class));
        mockMvc.perform(post("/transacao").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(transacao)))
                .andExpect(status().isUnprocessableEntity());
        verify(service, times(1)).receberTransacao(Mockito.any(Transacao.class));
    }
    @Test
    void receberTransacaoShouldReturnStatusCode422IfDataTransacaoNotBeInTheFuture() throws Exception {
        doThrow(new IllegalArgumentException("A data da transacao nao pode estar em um momento futuro."))
                .when(service).receberTransacao(any(Transacao.class));
        mockMvc.perform(post("/transacao").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(transacao)))
                .andExpect(status().isUnprocessableEntity());
        verify(service, times(1)).receberTransacao(Mockito.any(Transacao.class));
    }
    @Test
    void receberTransacaoShouldReturnStatusCode422IfValorTransacaoBeNegativoAndDataTransacaoNotBeInTheFuture() throws Exception {
        doThrow(new IllegalArgumentException("O valor da transacao nao pode ser negativo e a data da transacao nao pode estar em um momento futuro."))
                .when(service).receberTransacao(any(Transacao.class));
        mockMvc.perform(post("/transacao").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(transacao)))
                .andExpect(status().isUnprocessableEntity());
        verify(service, times(1)).receberTransacao(Mockito.any(Transacao.class));
    }
    @Test
    void receberTransacaoShouldReturnStatusCode400() throws Exception {
        doThrow(new IllegalArgumentException("payload errado."))
                .when(service).receberTransacao(any(Transacao.class));
        mockMvc.perform(post("/transacao").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(transacao)))
                .andExpect(status().isBadRequest());
        verify(service, times(1)).receberTransacao(Mockito.any(Transacao.class));
    }
    @Test
    void getTransacaoShouldReturnStatusCode200() throws Exception {
        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(new Transacao(100.00, OffsetDateTime.parse("2024-06-06T20:00:00.000-03:00")));
        transacoes.add(new Transacao(200.00, OffsetDateTime.parse("2024-06-06T20:05:00.000-03:00")));
        transacoes.add(new Transacao(300.00, OffsetDateTime.parse("2024-06-06T20:10:00.000-03:00")));

        when(service.getTransacao()).thenReturn(transacoes);

        mockMvc.perform(get("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacoes)))
                .andExpect(status().isOk());
        verify(service, times(1)).getTransacao();
    }
    @Test
    void getTransacaoShouldReturnStatusCode404IfListaIsEmpty() throws Exception {
        List<Transacao> transacoes = new ArrayList<>();

        doThrow(new IllegalArgumentException("A lista esta vazia")).when(service).getTransacao();

        mockMvc.perform(get("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transacoes)))
                .andExpect(status().isNotFound());
        verify(service, times(1)).getTransacao();
    }
    @Test
    void getEstatisticalTransacaoShouldReturnStatusCode200IfListaReturnsTransacoesWhenTempoIs60SegundosDefaultValeu() throws Exception {
        Estatistica estatistica = new Estatistica(5, 40002.25, 8000.45, 8000.45, 8000.45);
        int valorPadraoDeTempo = 60;
        when(service.getEstatistica(valorPadraoDeTempo)).thenReturn(estatistica);
        mockMvc.perform(get("/transacao/estatistica").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(estatistica)))
                .andExpect(status().isOk());
        verify(service, times(1)).getEstatistica(valorPadraoDeTempo);
    }
    @Test
    void getEstatisticalTransacaoShouldReturnStatusCode200IfListaReturnsTransacoesWhenTempoIsAnotherValeuButDefaultValeu() throws Exception {
        Estatistica estatistica = new Estatistica(5, 40002.25, 8000.45, 8000.45, 8000.45);
        when(service.getEstatistica(ArgumentMatchers.anyInt())).thenReturn(estatistica);
        mockMvc.perform(get("/transacao/estatistica").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(estatistica)))
                .andExpect(status().isOk());
        verify(service, times(1)).getEstatistica(ArgumentMatchers.anyInt());
    }
    @Test
    void getEstatisticalTransacaoShouldReturnStatusCode404IfListaPrincipalDeTransacoesIsEmpty() throws Exception {
        doThrow(new IllegalArgumentException("A lista de transacoes esta vazia")).when(service).getEstatistica(60);
        mockMvc.perform(get("/transacao/estatistica").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isNotFound());
        verify(service, times(1)).getEstatistica(60);
    }
    @Test
    void getEstatisticalTransacaoShouldReturnStatusCode404IfTheresNoTransacaoAtTheSpecifiedTimeWithDefaultTime() throws Exception {
        doThrow(new IllegalArgumentException("Nao ha registro de transacoes para o intervalo de tempo escolhido")).when(service).getEstatistica(60);
        mockMvc.perform(get("/transacao/estatistica").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isNotFound());
        verify(service, times(1)).getEstatistica(60);
    }
    @Test
    void getEstatisticalTransacaoShouldReturnStatusCode404IfTheresNoTransacaoAtTheSpecifiedTime() throws Exception {
        doThrow(new IllegalArgumentException("Nao ha registro de transacoes para o intervalo de tempo escolhido")).when(service).getEstatistica(ArgumentMatchers.anyInt());
        mockMvc.perform(get("/transacao/estatistica").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isNotFound());
        verify(service, times(1)).getEstatistica(ArgumentMatchers.anyInt());
    }
    @Test
    void deleteTransacaoShouldReturnStatusCode200IfListIsEmptyAfterDelete() throws Exception {
        doNothing().when(service).deletarTransacao();
        mockMvc.perform(delete("/transacao/delete")).andExpect(status().isOk());
        verify(service, times(1)).deletarTransacao();
    }
    @Test
    void deleteTransacaoShouldReturnStatusCode400IfListIsNotEmptyAfterDelete() throws Exception {
        doThrow(new IllegalArgumentException("Houve algum problema ao tentar apagar os itens da lista.")).when(service).deletarTransacao();
        mockMvc.perform(delete("/transacao/delete")).andExpect(status().isBadRequest());
        verify(service, times(1)).deletarTransacao();
    }
}