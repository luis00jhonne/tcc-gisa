package br.com.gisa.associados.model;

import br.com.gisa.associados.enums.SituacaoAssociadoEnum;
import br.com.gisa.associados.dto.model.AssociadoDTO;
import br.com.gisa.associados.enums.TipoPlanoEnum;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "associado", fetch=FetchType.EAGER)
    private List<AutorizacaoExameConsulta> historico;

    public AssociadoDTO convertEntityToDTO() {
        return new ModelMapper().map(this, AssociadoDTO.class);
    }
}
