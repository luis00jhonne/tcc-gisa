package br.com.gisa.conveniados.model;

import br.com.gisa.conveniados.enums.SituacaoConveniadoEnum;
import br.com.gisa.conveniados.dto.model.ConveniadoDTO;
import br.com.gisa.conveniados.enums.TipoPlanoEnum;
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
public class Conveniado implements Serializable {

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
    private SituacaoConveniadoEnum situacao;
    private TipoPlanoEnum tipoPlano;
    private Date dataCadastro;
    private Date dataAtualizacao;

    public ConveniadoDTO convertEntityToDTO() {
        return new ModelMapper().map(this, ConveniadoDTO.class);
    }
}
