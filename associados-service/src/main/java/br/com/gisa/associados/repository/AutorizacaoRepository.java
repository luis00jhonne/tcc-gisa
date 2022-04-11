package br.com.gisa.associados.repository;

import br.com.gisa.associados.model.AutorizacaoExameConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface AutorizacaoRepository extends JpaRepository<AutorizacaoExameConsulta, Long> {

    Optional<AutorizacaoExameConsulta> findOneByCodigoAutorizacao(String codigoAutorizacao);
}
