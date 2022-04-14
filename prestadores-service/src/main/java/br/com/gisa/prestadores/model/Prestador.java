package br.com.gisa.prestadores.model;

import br.com.gisa.prestadores.enums.SituacaoPrestadorEnum;
import br.com.gisa.prestadores.dto.model.PrestadorDTO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name="prestadores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Prestador implements Serializable {

    private @Id @GeneratedValue Long id;
    private String nome;
    private @Column(unique = true) String cpf;
    private String email;
    private Date dataNascimento;
    private String celular;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String numero;
    private String uf;
    private String complemento;
    private String cep;
    private SituacaoPrestadorEnum situacao;
    private String especialidade;
    private Date dataCadastro;
    private Date dataAtualizacao;

    public PrestadorDTO convertEntityToDTO() {
        return new ModelMapper().map(this, PrestadorDTO.class);
    }
}
