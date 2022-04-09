package br.com.gisa.prestadores.repository;

import br.com.gisa.prestadores.model.Associado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    Page<Associado> findByNome(String nome, Pageable pageable);
    Optional<Associado> findOneByCpf(String cpf);

}
