package br.com.gisa.prestadores.model;

import br.com.gisa.prestadores.enums.SituacaoAssociadoEnum;
import br.com.gisa.prestadores.dto.model.AssociadoDTO;
import br.com.gisa.prestadores.enums.TipoPlanoEnum;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name="associados")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Associado implements Serializable {

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
    private SituacaoAssociadoEnum situacao;
    private TipoPlanoEnum tipoPlano;
    private Date dataCadastro;
    private Date dataAtualizacao;

    public AssociadoDTO convertEntityToDTO() {
        return new ModelMapper().map(this, AssociadoDTO.class);
    }
}
