package org.example.desafioapitransacao.service;

import org.example.desafioapitransacao.DTO.Estatistica;
import org.example.desafioapitransacao.DTO.Transacao;
import org.example.desafioapitransacao.repository.TransacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
    @Mock
    private TransacaoRepository transacaoRepository;

    private Transacao transacao;

    @InjectMocks
    private TransacaoService transacaoService;

    @BeforeEach
    public void setUp() {
        transacao = new Transacao(145.00, OffsetDateTime.parse("2024-06-06T20:10:25.201-03:00"));
    }
    @Test
    void receberTransacao() {
        transacaoService.receberTransacao(transacao);
        verify(transacaoRepository, times(1)).addTransacao(transacao);

    }
    @Test
    void receberTransacaoShouldThrowExceptionWhenValorIsLessThanZero() {
        Transacao transacaoIncorreta = new Transacao(-145.00, OffsetDateTime.parse("2024-06-06T20:10:25.201-03:00"));
        try{
            transacaoService.receberTransacao(transacaoIncorreta);
        }catch(IllegalArgumentException e){
            assert e.getMessage().equals("O valor da transacao nao pode ser negativo.");
        }
        verify(transacaoRepository, times(0)).addTransacao(transacaoIncorreta);
    }
    @Test
    void receberTransacaoShouldThrowExceptionWhenDateIshigherThancCurrentDate() {
        OffsetDateTime dataHoraIncorreta = OffsetDateTime.now().plusDays(1);
        Transacao transacaoIncorreta = new Transacao(145.00, dataHoraIncorreta);
        try{
            transacaoService.receberTransacao(transacaoIncorreta);
        }catch(IllegalArgumentException e){
            assert e.getMessage().equals("A data da transacao nao pode estar em um momento futuro.");
        }
        verify(transacaoRepository, times(0)).addTransacao(transacaoIncorreta);
    }
    @Test
    void receberTransacaoShouldThrowExceptionWhenValorIsLessThanZeroAndDateIshigherThancCurrentDate() {
        OffsetDateTime dataHoraIncorreta = OffsetDateTime.now().plusDays(1);
        Transacao transacaoIncorreta = new Transacao(-145.00, dataHoraIncorreta);
        try{
            transacaoService.receberTransacao(transacaoIncorreta);
        }catch(IllegalArgumentException e){
            assert e.getMessage().equals("O valor da transacao nao pode ser negativo e a data da transacao nao pode estar em um momento futuro.");
        }
        verify(transacaoRepository, times(0)).addTransacao(transacaoIncorreta);
    }
    @Test
    void getTransacaoShouldreturnAListOfTransacao() {
        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(new Transacao(100.00, OffsetDateTime.parse("2024-06-06T20:00:00.000-03:00")));
        transacoes.add(new Transacao(200.00, OffsetDateTime.parse("2024-06-06T20:05:00.000-03:00")));
        transacoes.add(new Transacao(300.00, OffsetDateTime.parse("2024-06-06T20:10:00.000-03:00")));

        when(transacaoRepository.getTransacoes()).thenReturn(transacoes);
        List<Transacao> transacoesRecebidas = transacaoService.getTransacao();
        assertEquals(transacoes, transacoesRecebidas);
        verify(transacaoRepository, times(1)).getTransacoes();
    }
    @Test
    void getTransacaoShouldThrowAnExceptionIfListOfTransacaoIsEmpty() {
        List<Transacao> transacoes = new ArrayList<>();
        when(transacaoRepository.getTransacoes()).thenReturn(transacoes);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transacaoService.getTransacao();
        });
        assertEquals("A lista esta vazia", exception.getMessage());
    }
    @Test
    void getEstatisticaShouldReturnEstatisticaOfTrasacaoatLast60Seconds() {
        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(new Transacao(100.00, OffsetDateTime.now().minusSeconds(45)));
        transacoes.add(new Transacao(200.00, OffsetDateTime.now().minusSeconds(10)));
        transacoes.add(new Transacao(300.00, OffsetDateTime.now().minusSeconds(59)));

        when(transacaoRepository.getTransacoes()).thenReturn(transacoes);

        Estatistica estatistica = transacaoService.getEstatistica(60);

        int expectedCount = 3;
        double expectedSum = 600.00;
        double expectedAverage = 200.00;
        double expectedMax = 300.00;
        double expectedMin = 100.00;

        assertEquals(expectedCount, estatistica.getCount());
        assertEquals(expectedSum, estatistica.getSum(), 0.001);
        assertEquals(expectedAverage, estatistica.getAvg(), 0.001);
        assertEquals(expectedMax, estatistica.getMax(), 0.001);
        assertEquals(expectedMin, estatistica.getMin(), 0.001);

        verify(transacaoRepository, times(1)).getTransacoes();
    }
    @Test
    void getEstatisticaShouldReturnEstatisticaOfTrasacaoatWithTempohigherThan60Seconds() {
        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(new Transacao(100.00, OffsetDateTime.now().minusSeconds(45)));
        transacoes.add(new Transacao(200.00, OffsetDateTime.now().minusSeconds(10)));
        transacoes.add(new Transacao(300.00, OffsetDateTime.now().minusSeconds(100)));

        when(transacaoRepository.getTransacoes()).thenReturn(transacoes);

        Estatistica estatistica = transacaoService.getEstatistica(120);

        int expectedCount = 3;
        double expectedSum = 600.00;
        double expectedAverage = 200.00;
        double expectedMax = 300.00;
        double expectedMin = 100.00;

        assertEquals(expectedCount, estatistica.getCount());
        assertEquals(expectedSum, estatistica.getSum(), 0.001);
        assertEquals(expectedAverage, estatistica.getAvg(), 0.001);
        assertEquals(expectedMax, estatistica.getMax(), 0.001);
        assertEquals(expectedMin, estatistica.getMin(), 0.001);

        verify(transacaoRepository, times(1)).getTransacoes();
    }
    @Test
    void getEstatisticaShouldThrowAnExceptionIfListOfTransacaoIsEmpty() {
        List<Transacao> transacoes = new ArrayList<>();
        when(transacaoRepository.getTransacoes()).thenReturn(transacoes);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transacaoService.getEstatistica(60);
        });
        assertEquals("A lista de transacoes esta vazia.", exception.getMessage());

        verify(transacaoRepository, times(1)).getTransacoes();
    }
    @Test
    void getEstatisticaShouldThrowAnExceptionIfTheresNoTransacoesInTheTempoSpecified() {
        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(new Transacao(100.00, OffsetDateTime.now().minusSeconds(70)));
        transacoes.add(new Transacao(200.00, OffsetDateTime.now().minusSeconds(80)));
        transacoes.add(new Transacao(300.00, OffsetDateTime.now().minusSeconds(100)));

        when(transacaoRepository.getTransacoes()).thenReturn(transacoes);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transacaoService.getEstatistica(60);
        });
        assertEquals("Nao ha registro de transacoes para o intervalo de tempo escolhido", exception.getMessage());

        verify(transacaoRepository, times(1)).getTransacoes();
    }
    @Test
    void deleteTransacaoShouldClearTheListOfTransacoes() {
        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(new Transacao(100.00, OffsetDateTime.now()));
        transacoes.add(new Transacao(200.00, OffsetDateTime.now()));

        when(transacaoRepository.getTransacoes())
                .thenReturn(transacoes)
                .thenReturn(Collections.emptyList());

        transacaoService.deletarTransacao();

        verify(transacaoRepository, times(1)).deleteTransacoes();
        assertTrue(transacaoRepository.getTransacoes().isEmpty());
    }
    @Test
    void deleteTransacaoShouldThrowAnExceptionIfDeleteDoesntRunAndListOfTransacoesIsntEmptyAfterDelete() {
        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(new Transacao(100.00, OffsetDateTime.now()));
        transacoes.add(new Transacao(200.00, OffsetDateTime.now()));

        when(transacaoRepository.getTransacoes()).thenReturn(transacoes).thenReturn(transacoes);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            transacaoService.deletarTransacao();
        });

        verify(transacaoRepository, times(1)).deleteTransacoes();
        assertEquals("Houve algum problema ao tentar apagar os itens da lista.", exception.getMessage());
    }
}

