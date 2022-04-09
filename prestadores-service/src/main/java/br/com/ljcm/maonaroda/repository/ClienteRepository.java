package br.com.ljcm.maonaroda.repository;

import br.com.ljcm.maonaroda.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Page<Cliente> findByNome(String nome, Pageable pageable);
    Optional<Cliente> findOneByCpf(String cpf);

}
