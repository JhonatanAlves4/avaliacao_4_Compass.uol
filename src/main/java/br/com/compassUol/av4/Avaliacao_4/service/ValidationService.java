package br.com.compassUol.av4.Avaliacao_4.service;

import br.com.compassUol.av4.Avaliacao_4.dto.request.RequestAssociadoDTO;
import br.com.compassUol.av4.Avaliacao_4.dto.request.RequestPartidoDTO;
import br.com.compassUol.av4.Avaliacao_4.enums.CargoPolitico;
import br.com.compassUol.av4.Avaliacao_4.enums.Ideologia;
import br.com.compassUol.av4.Avaliacao_4.enums.Sexo;
import br.com.compassUol.av4.Avaliacao_4.exceptions.CargoPoliticoException;
import br.com.compassUol.av4.Avaliacao_4.exceptions.IdeologiaException;
import br.com.compassUol.av4.Avaliacao_4.exceptions.SexoException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ValidationService {

    public void validateCargo(RequestAssociadoDTO requestAssociadoDTO) {
        boolean isValid = Arrays.stream(CargoPolitico.values()).anyMatch(cargoPolitico -> cargoPolitico.getValue().equals(requestAssociadoDTO.getCargoPolitico()));

        if (!isValid) {
            throw new CargoPoliticoException();
        }
    }

    public void validateIdeologia(RequestPartidoDTO requestPartidoDTO) {
        boolean isValid = Arrays.stream(Ideologia.values()).anyMatch(ideologia -> ideologia.getValue().equals(requestPartidoDTO.getIdeologia()));

        if (!isValid) {
            throw new IdeologiaException();
        }
    }

    public void validateSexo(RequestAssociadoDTO requestAssociadoDTO) {
        boolean isValid = Arrays.stream(Sexo.values()).anyMatch(sexo -> sexo.getValue().equals(requestAssociadoDTO.getSexo()));

        if (!isValid) {
            throw new SexoException();
        }
    }

}
