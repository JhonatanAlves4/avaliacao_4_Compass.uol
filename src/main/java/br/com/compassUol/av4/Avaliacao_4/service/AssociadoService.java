package br.com.compassUol.av4.Avaliacao_4.service;

import br.com.compassUol.av4.Avaliacao_4.dto.request.RequestAssociadoDTO;
import br.com.compassUol.av4.Avaliacao_4.dto.request.RequestPartidoAssociado;
import br.com.compassUol.av4.Avaliacao_4.dto.response.ResponseAssociadoDTO;
import br.com.compassUol.av4.Avaliacao_4.exceptions.Associado404Exception;
import br.com.compassUol.av4.Avaliacao_4.model.Associado;
import br.com.compassUol.av4.Avaliacao_4.repository.AssociadoRepository;
import br.com.compassUol.av4.Avaliacao_4.repository.PartidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private ModelMapper modelMapper;

    public List<ResponseAssociadoDTO> findAll(String cargoPolitico) {
        if (cargoPolitico == null) {
            List<Associado> findAll = associadoRepository.findByOrderByNomeAsc();
            List<ResponseAssociadoDTO> dtos = findAll.stream().map(associado -> modelMapper.map(associado, ResponseAssociadoDTO.class)).collect(Collectors.toList());
            return dtos;
        } else {
            List<Associado> findByCargoPolitico = associadoRepository.findByCargoPolitico(cargoPolitico);
            List<ResponseAssociadoDTO> dtos = findByCargoPolitico.stream().map(associado -> modelMapper.map(associado, ResponseAssociadoDTO.class)).collect(Collectors.toList());
            return dtos;
        }
    }

    public ResponseAssociadoDTO post(@Valid RequestAssociadoDTO request) {
        validationService.validateCargo(request);
        validationService.validateSexo(request);
        Associado associado = modelMapper.map(request, Associado.class);
        Associado associadoSaved = associadoRepository.save(associado);
        return modelMapper.map(associadoSaved, ResponseAssociadoDTO.class);
    }

    public ResponseAssociadoDTO postPartidoDoAssociado(RequestPartidoAssociado request) {
        Associado associadoComPartido = request.cadastrarPartido(partidoRepository, associadoRepository);
        return modelMapper.map(associadoComPartido, ResponseAssociadoDTO.class);
    }

    public ResponseAssociadoDTO findById(Long id) {
        Associado associadoId = associadoRepository.findById(id).orElseThrow(Associado404Exception::new);
        return modelMapper.map(associadoId, ResponseAssociadoDTO.class);
    }

    public ResponseAssociadoDTO update(Long id, RequestAssociadoDTO request) {
        validationService.validateCargo(request);
        validationService.validateSexo(request);
        Associado associadoUpdate = associadoRepository.findById(id).orElseThrow(Associado404Exception::new);
        modelMapper.map(request, associadoUpdate);
        return modelMapper.map(associadoUpdate, ResponseAssociadoDTO.class);
    }

    public void deleteById(Long id) {
        associadoRepository.findById(id).orElseThrow(Associado404Exception::new);
        associadoRepository.deleteById(id);
    }

    public void deleteAssociadoDoPartido(Long associadoId, RequestPartidoAssociado request) {
       request.deletarPartido(associadoId, associadoRepository);
    }

}
