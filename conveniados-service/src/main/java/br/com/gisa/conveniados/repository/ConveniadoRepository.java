package br.com.gisa.conveniados.repository;

import br.com.gisa.conveniados.model.Conveniado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface ConveniadoRepository extends JpaRepository<Conveniado, Long> {

    Page<Conveniado> findByNome(String nome, Pageable pageable);
    Optional<Conveniado> findOneByCpf(String cpf);

}
