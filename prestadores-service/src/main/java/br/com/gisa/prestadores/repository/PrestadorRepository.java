package br.com.gisa.prestadores.repository;

import br.com.gisa.prestadores.model.Prestador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface PrestadorRepository extends JpaRepository<Prestador, Long> {

    Page<Prestador> findByNome(String nome, Pageable pageable);
    Optional<Prestador> findOneByCpf(String cpf);

}
