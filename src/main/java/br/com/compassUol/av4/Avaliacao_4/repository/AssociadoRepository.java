package br.com.compassUol.av4.Avaliacao_4.repository;

import br.com.compassUol.av4.Avaliacao_4.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    List<Associado> findByOrderByNomeAsc();
    List<Associado> findByCargoPolitico(String cargoPolitico);
}
