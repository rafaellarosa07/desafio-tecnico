package br.com.teamcubation.challenge;

import br.com.teamcubation.challenge.controller.TransacaoController;
import br.com.teamcubation.challenge.service.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransacaoController.class)
class TransacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransacaoService transacaoService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void receberTransacao() throws Exception {
        String transacaoJson = "{\"valor\": 100.0, \"dataHora\": \"2024-06-08T10:55:00-03:00\"}";

        mockMvc.perform(post("/transacao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(transacaoJson))
                .andExpect(status().isCreated());
    }
}
