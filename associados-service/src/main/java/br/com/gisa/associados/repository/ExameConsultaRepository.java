package br.com.gisa.associados.repository;

import br.com.gisa.associados.model.ExameConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface ExameConsultaRepository extends JpaRepository<ExameConsulta, Long> {

}
