package com.github.daniellimadev.apitransacao.service;

import com.github.daniellimadev.apitransacao.dto.EstatisticasDTO;
import com.github.daniellimadev.apitransacao.dto.TransacaoDTO;
import com.github.daniellimadev.apitransacao.exceptions.ValidacaoException;
import com.github.daniellimadev.apitransacao.model.Transacao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;

    @BeforeEach
    public void setup() {
        transacaoService.deletar();
    }

    @Test
    public void salvaTransacaoTest() {

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        TransacaoDTO transacaoDTO = new TransacaoDTO("100.00", formatter.format(offsetDateTime));

        Transacao transacao = transacaoService.salvar(transacaoDTO);

        assertEquals(Double.parseDouble(transacaoDTO.getValor()), transacao.getValor());
        assertEquals(OffsetDateTime.parse(transacaoDTO.getDataHora()), transacao.getDataHora());
    }

    @Test
    public void deletarTransacaoTest() {

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        TransacaoDTO transacaoDTO = new TransacaoDTO("200.00", formatter.format(offsetDateTime));

        transacaoService.salvar(transacaoDTO);
        transacaoService.deletar();
        EstatisticasDTO estatisticasDTO = transacaoService.estatisticas();

        assertEquals(estatisticasDTO.getCount(), 0);
    }

    @Test
    public void estatisticasTest() throws InterruptedException {

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        TransacaoDTO transacaoDTO = new TransacaoDTO("200.00", formatter.format(offsetDateTime));
        transacaoService.salvar(transacaoDTO);

        Thread.sleep(10);

        OffsetDateTime offsetDateTime2 = OffsetDateTime.now();
        DateTimeFormatter formatter2 = DateTimeFormatter.ISO_DATE_TIME;

        transacaoDTO = new TransacaoDTO("300.00", formatter2.format(offsetDateTime2));
        transacaoService.salvar(transacaoDTO);

        EstatisticasDTO estatísticasDTO = transacaoService.estatisticas();

        assertEquals(estatísticasDTO.getCount(), 2);
        assertEquals(estatísticasDTO.getSum(), 500.0);
        assertEquals(estatísticasDTO.getAvg(), 250.0);
        assertEquals(estatísticasDTO.getMin(), 200.0);
        assertEquals(estatísticasDTO.getMax(), 300.0);
    }

    @Test
    public void dataFuturoTest() {

        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        OffsetDateTime offsetDateTime2 = offsetDateTime.plusSeconds(1000);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        TransacaoDTO transacaoDTO = new TransacaoDTO("150.00", formatter.format(offsetDateTime2));

        assertThrows(ValidacaoException.class, () -> transacaoService.salvar(transacaoDTO));
    }

    @Test
    public void valorMenorQueZeroTest() {

        OffsetDateTime offsetDateTime = OffsetDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

        TransacaoDTO transacaoDTO = new TransacaoDTO("-1", formatter.format(offsetDateTime));

        assertThrows(ValidacaoException.class, () -> transacaoService.salvar(transacaoDTO));
    }

}

