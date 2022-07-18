package br.com.compassUol.av4.Avaliacao_4.service;

import br.com.compassUol.av4.Avaliacao_4.dto.request.RequestPartidoDTO;
import br.com.compassUol.av4.Avaliacao_4.dto.response.ResponseAssociadoDTO;
import br.com.compassUol.av4.Avaliacao_4.dto.response.ResponsePartidoDTO;
import br.com.compassUol.av4.Avaliacao_4.exceptions.Partido404Exception;
import br.com.compassUol.av4.Avaliacao_4.model.Associado;
import br.com.compassUol.av4.Avaliacao_4.model.Partido;
import br.com.compassUol.av4.Avaliacao_4.repository.PartidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private ModelMapper modelMapper;

    public List<ResponsePartidoDTO> findAll(String ideologia) {
        if (ideologia == null) {
            List<Partido> findAll = partidoRepository.findAll();
            List<ResponsePartidoDTO> dtos = findAll.stream().map(partido -> modelMapper.map(partido, ResponsePartidoDTO.class)).collect(Collectors.toList());
            return dtos;
        } else {
            List<Partido> findByIdeologia = partidoRepository.findByIdeologia(ideologia);
            List<ResponsePartidoDTO> dtos = findByIdeologia.stream().map(partido -> modelMapper.map(partido, ResponsePartidoDTO.class)).collect(Collectors.toList());
            return dtos;
        }
    }

    public ResponsePartidoDTO post(RequestPartidoDTO request) {
        validationService.validateIdeologia(request);
        Partido partido = modelMapper.map(request, Partido.class);
        Partido partidoSaved = partidoRepository.save(partido);

        return modelMapper.map(partidoSaved, ResponsePartidoDTO.class);
    }

    public ResponsePartidoDTO findById(Long id) {
        Partido partidoId = partidoRepository.findById(id).orElseThrow(Partido404Exception::new);
        return modelMapper.map(partidoId, ResponsePartidoDTO.class);
    }

    public List<ResponseAssociadoDTO> getAssociadosPartido(Long id) {
        Partido partido = partidoRepository.findById(id).orElseThrow(Partido404Exception::new);
        List<Associado> associadosPartido = partido.getAssociados();
        List<ResponseAssociadoDTO> dtos =
                associadosPartido.stream().map(partidoAss -> modelMapper.map(partidoAss, ResponseAssociadoDTO.class)).collect(Collectors.toList());
        return dtos;
    }

    public ResponsePartidoDTO update(Long id, RequestPartidoDTO request) {
        validationService.validateIdeologia(request);
        Partido partidoUpdate = partidoRepository.findById(id).orElseThrow(Partido404Exception::new);
        modelMapper.map(request, partidoUpdate);
        return modelMapper.map(partidoUpdate, ResponsePartidoDTO.class);

    }

    public void deleteById(Long id) {
        partidoRepository.findById(id).orElseThrow(Partido404Exception::new);
        partidoRepository.deleteById(id);
    }
}
