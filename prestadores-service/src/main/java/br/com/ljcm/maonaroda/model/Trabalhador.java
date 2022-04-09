package br.com.ljcm.maonaroda.model;

import br.com.ljcm.maonaroda.enums.SituacaoCadastroEnum;
import br.com.ljcm.maonaroda.dto.model.TrabalhadorDTO;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name="trabalhador")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Trabalhador implements Serializable {

    private @Id @GeneratedValue Long id;
    private String nome;
    private @Column(unique = true) String cpf;
    private String email;
    private String especialidade;
    private Boolean mei;
    private Date dataNascimento;
    private String celular;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String numero;
    private String uf;
    private String complemento;
    private String cep;
    private String localizacao;
    private SituacaoCadastroEnum situacao;
    private Date dataCadastro;
    private Date dataAtualizacao;

    public TrabalhadorDTO convertEntityToDTO() {
        return new ModelMapper().map(this, TrabalhadorDTO.class);
    }
}
