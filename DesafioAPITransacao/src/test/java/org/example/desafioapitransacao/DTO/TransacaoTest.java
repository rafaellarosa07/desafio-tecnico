package org.example.desafioapitransacao.DTO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

public class TransacaoTest {
    @Test
    void getValor(){
        Transacao transacao = new Transacao(1000.00, OffsetDateTime.parse("2024-06-07T10:10:25.201-03:00"));
        double valor = transacao.getValor();
        Assertions.assertEquals(1000.00, valor);
    }
    @Test
    void setValor(){
        Transacao transacao = new Transacao(0.0, OffsetDateTime.parse("2024-06-07T10:10:25.201-03:00"));
        transacao.setValor(1200.00);
        double valor = transacao.getValor();
        Assertions.assertEquals(1200.00, valor);
    }@Test
    void getDataHora(){
        Transacao transacao = new Transacao(0.0, OffsetDateTime.parse("2024-06-07T10:10:25.201-03:00"));
        OffsetDateTime dataHora = transacao.getDataHora();
        Assertions.assertEquals(OffsetDateTime.parse("2024-06-07T10:10:25.201-03:00"), dataHora);
    }
    @Test
    void setDataHora(){
        Transacao transacao = new Transacao(0.0, OffsetDateTime.parse("2024-06-07T10:10:25.201-03:00"));
        transacao.setDataHora(OffsetDateTime.now());
        OffsetDateTime dataHora = transacao.getDataHora();
        Assertions.assertEquals(OffsetDateTime.now(), dataHora);
    }
}
