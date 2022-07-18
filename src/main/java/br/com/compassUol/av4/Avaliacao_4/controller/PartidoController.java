package br.com.compassUol.av4.Avaliacao_4.controller;

import br.com.compassUol.av4.Avaliacao_4.dto.request.RequestPartidoDTO;
import br.com.compassUol.av4.Avaliacao_4.dto.response.ResponseAssociadoDTO;
import br.com.compassUol.av4.Avaliacao_4.dto.response.ResponsePartidoDTO;
import br.com.compassUol.av4.Avaliacao_4.service.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/partidos")
public class PartidoController {

    //@Autowired
    final PartidoService partidoService;

    @Autowired
    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    @GetMapping
    public ResponseEntity<List<ResponsePartidoDTO>> getPartidos(@RequestParam(required = false) String ideologia) {
        List<ResponsePartidoDTO> responsePartidoDTOS = partidoService.findAll(ideologia);
        return ResponseEntity.ok(responsePartidoDTOS);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponsePartidoDTO> postPartido(@RequestBody @Valid RequestPartidoDTO request) {
        ResponsePartidoDTO response = partidoService.post(request);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePartidoDTO> getPartidoById(@PathVariable @Valid Long id) {
        ResponsePartidoDTO partidoById = partidoService.findById(id);
        return ResponseEntity.ok(partidoById);
    }

    @GetMapping("/{id}/associados")
    @Transactional
    public ResponseEntity<List<ResponseAssociadoDTO>> getAssociadosPorPartido(@PathVariable Long id) {
        List<ResponseAssociadoDTO> associadosPorPartido = partidoService.getAssociadosPartido(id);
        return ResponseEntity.ok(associadosPorPartido);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponsePartidoDTO> putPartido(@RequestBody @Valid RequestPartidoDTO request, @PathVariable Long id) {
        ResponsePartidoDTO update = partidoService.update(id, request);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponsePartidoDTO> deletePartido(@PathVariable Long id) {
        partidoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
