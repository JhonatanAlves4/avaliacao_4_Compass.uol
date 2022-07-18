package br.com.compassUol.av4.Avaliacao_4.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class AssociadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveriaDarErroDeNomeVazio() throws Exception {
        URI uri = new URI("/associados");

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(
                                stringJson("",
                                        "Deputado Estadual",
                                        "01-05-2003",
                                        "Masculino"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));

    }

    @Test
    void deveriaDarErroDeCargoPoliticoInvalido() throws Exception {
        URI uri = new URI("/associados");

        mockMvc.perform(MockMvcRequestBuilders.post(uri).content(
                                stringJson("José",
                                        "Jardineiro",
                                        "13-09-1988",
                                        "Masculino"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));

    }

    @Test
    void deveriaSelecionarAssociadoPorId() throws Exception {
        URI uriPost = new URI("/associados");
        mockMvc.perform(MockMvcRequestBuilders.post(uriPost).content(
                        stringJson("José",
                                "Deputado Federal",
                                "13-09-1988",
                                "Masculino"))
                .contentType(MediaType.APPLICATION_JSON));

        URI uri = new URI("/associados/2");
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }

    @Test
    void deveriaDeletarUmAssociado() throws Exception {
        URI uriPost = new URI("/associados");
        mockMvc.perform(MockMvcRequestBuilders.post(uriPost).content(
                        stringJson("José",
                                "Deputado Federal",
                                "13-09-1988",
                                "Masculino"))
                .contentType(MediaType.APPLICATION_JSON));

        URI uriDelete = new URI("/associados/1");
        mockMvc.perform(MockMvcRequestBuilders.delete(uriDelete)
                        .contentType(MediaType.APPLICATION_JSON));

        URI uriGet = new URI("/associados/1");
        mockMvc.perform(MockMvcRequestBuilders.get(uriGet)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(404));



    }


    private String stringJson(String nome, String cargoPolitico, String dataNascimento, String sexo) {
        return "{" + "\"nome\":" + "\"" + nome + "\"" + ","
                +"\"cargoPolitico\":" + "\"" + cargoPolitico + "\"" + ","
                + "\"dataNascimento\":" + "\"" + dataNascimento + "\"" + ","
                + "\"sexo\":" + "\"" + sexo + "\"" + "}";
    }

}