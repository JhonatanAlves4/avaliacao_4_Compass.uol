package br.com.compassUol.av4.Avaliacao_4.controller;

import br.com.compassUol.av4.Avaliacao_4.exceptions.Partido404Exception;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PartidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveriaDarErroDeNomeVazio() throws Exception {
        URI uri = new URI("/partidos");

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(
                stringJson("", "PSTU", "Esquerda", "17-07-2022"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));

    }

    @Test
    void deveriaDarErroDeSiglaVazia() throws Exception {
        URI uri = new URI("/partidos");

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(
                                stringJson("Partido Socialista dos Trabalhadores Unificado",
                                        "",
                                        "Esquerda",
                                        "17-07-2022"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));

    }

    @Test
    void deveriaDarErroDeIdeologiaNaoPermitida() throws Exception {
        URI uri = new URI("/partidos");

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(
                                stringJson("Partido Socialista dos Trabalhadores Unificado",
                                        "PSTU",
                                        "Extrema-Esquerda",
                                        "17-07-2022"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));

    }

    @Test
    void deveriaSelecionarPartidoPorId() throws Exception {
        URI uriPost = new URI("/partidos");
        mockMvc.perform(MockMvcRequestBuilders.post(uriPost).content(
                        stringJson("Partido Socialista dos Trabalhadores Unificado",
                                "PSTU",
                                "Esquerda",
                                "17-07-2022"))
                .contentType(MediaType.APPLICATION_JSON));

        URI uri = new URI("/partidos/2");
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    void deveriaDeletarUmPartido() throws Exception {
        URI uriPost = new URI("/partidos");
        mockMvc.perform(MockMvcRequestBuilders.post(uriPost).content(
                        stringJson("Partido Socialista dos Trabalhadores Unificado",
                                "PSTU",
                                "Esquerda",
                                "17-07-2022"))
                .contentType(MediaType.APPLICATION_JSON));

        URI uriDelete = new URI("/partidos/1");
        mockMvc.perform(MockMvcRequestBuilders.delete(uriDelete)
                .contentType(MediaType.APPLICATION_JSON));

        URI uriGet = new URI("/partidos/1");
        mockMvc.perform(MockMvcRequestBuilders.get(uriGet)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404));
    }


    private String stringJson(String nome, String sigla, String ideologia, String dataFundacao) {
        return "{" + "\"nome\":" + "\"" + nome + "\"" + ","
                +"\"sigla\":" + "\"" + sigla + "\"" + ","
                + "\"ideologia\":" + "\"" + ideologia + "\"" + ","
                + "\"dataFundacao\":" + "\"" + dataFundacao + "\"" + "}";
    }

}