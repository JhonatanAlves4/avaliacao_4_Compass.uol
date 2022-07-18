package br.com.compassUol.av4.Avaliacao_4.dto.request;

import br.com.compassUol.av4.Avaliacao_4.exceptions.Associado404Exception;
import br.com.compassUol.av4.Avaliacao_4.exceptions.Partido404Exception;
import br.com.compassUol.av4.Avaliacao_4.model.Associado;
import br.com.compassUol.av4.Avaliacao_4.model.Partido;
import br.com.compassUol.av4.Avaliacao_4.repository.AssociadoRepository;
import br.com.compassUol.av4.Avaliacao_4.repository.PartidoRepository;

public class RequestPartidoAssociado {

    private Long idAssociado;
    private Long idPartido;

    public Associado cadastrarPartido(PartidoRepository partidoRepository, AssociadoRepository associadoRepository) {
        Associado associado = associadoRepository.findById(idAssociado).orElseThrow(Associado404Exception::new);
        Partido partido = partidoRepository.findById(idPartido).orElseThrow(Partido404Exception::new);
        associado.setPartido(partido);

        return associado;
    }

    public void deletarPartido(Long associadoId, AssociadoRepository associadoRepository) {
        Associado associado = associadoRepository.findById(associadoId).orElseThrow(Associado404Exception::new);
        associado.setPartido(null);

        associadoRepository.save(associado);
    }

    public Long getIdAssociado() {
        return idAssociado;
    }

    public void setIdAssociado(Long idAssociado) {
        this.idAssociado = idAssociado;
    }

    public Long getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Long idPartido) {
        this.idPartido = idPartido;
    }
}
