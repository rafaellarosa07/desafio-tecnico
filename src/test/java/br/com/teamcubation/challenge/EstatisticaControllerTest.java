package br.com.teamcubation.challenge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class EstatisticaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetEstatistica() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/estatistica")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.count").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sum").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.avg").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.min").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.max").isNumber());
    }
}
