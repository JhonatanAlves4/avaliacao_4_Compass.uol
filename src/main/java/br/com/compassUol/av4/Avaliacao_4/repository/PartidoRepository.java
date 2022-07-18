package br.com.compassUol.av4.Avaliacao_4.repository;

import br.com.compassUol.av4.Avaliacao_4.model.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {

    List<Partido> findByIdeologia(String ideologia);

}
