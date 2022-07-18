package br.com.compassUol.av4.Avaliacao_4.controller;

import br.com.compassUol.av4.Avaliacao_4.dto.request.RequestAssociadoDTO;
import br.com.compassUol.av4.Avaliacao_4.dto.request.RequestPartidoAssociado;
import br.com.compassUol.av4.Avaliacao_4.dto.response.ResponseAssociadoDTO;
import br.com.compassUol.av4.Avaliacao_4.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/associados")
public class AssociadoController {

    @Autowired
    private AssociadoService associadoService;

    @GetMapping
    public ResponseEntity<List<ResponseAssociadoDTO>> getAssociados(@RequestParam(required = false) String cargoPolitico,
                            @RequestParam(required = false, value = "nome") String nome) {
        List<ResponseAssociadoDTO> responseAssociadoDTOS = associadoService.findAll(cargoPolitico);
        return ResponseEntity.ok(responseAssociadoDTOS);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseAssociadoDTO> postAssociado(@RequestBody @Valid RequestAssociadoDTO request) {
        ResponseAssociadoDTO response = associadoService.post(request);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/partidos")
    @Transactional
    public ResponseEntity<ResponseAssociadoDTO> postAssociadoPartido(@RequestBody RequestPartidoAssociado request) {
        ResponseAssociadoDTO response = associadoService.postPartidoDoAssociado(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseAssociadoDTO> getAssociadoById(@PathVariable Long id) {
        ResponseAssociadoDTO associadoById = associadoService.findById(id);
        return ResponseEntity.ok(associadoById);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseAssociadoDTO> putAssociado(@RequestBody @Valid RequestAssociadoDTO request, @PathVariable Long id) {
        ResponseAssociadoDTO update = associadoService.update(id, request);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseAssociadoDTO> deleteAssociado(@PathVariable Long id) {
        associadoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{associadoId}/partidos/{partidoId}")
    @Transactional
    public ResponseEntity<ResponseAssociadoDTO> deleteAssociadoDoPartido(@PathVariable Long associadoId, @PathVariable Long partidoId, RequestPartidoAssociado request) {
        associadoService.deleteAssociadoDoPartido(associadoId, request);
        return ResponseEntity.ok().build();
    }


}
