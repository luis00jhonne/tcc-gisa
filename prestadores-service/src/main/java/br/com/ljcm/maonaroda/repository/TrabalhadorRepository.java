package br.com.ljcm.maonaroda.repository;

import br.com.ljcm.maonaroda.model.Trabalhador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface TrabalhadorRepository extends JpaRepository<Trabalhador, Long> {

    Page<Trabalhador> findByNome(String nome, Pageable pageable);
    Optional<Trabalhador> findOneByCpf(String cpf);

}
