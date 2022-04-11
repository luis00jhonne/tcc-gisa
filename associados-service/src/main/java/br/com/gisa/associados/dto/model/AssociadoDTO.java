package br.com.gisa.associados.dto.model;

import br.com.gisa.associados.enums.TipoPlanoEnum;
import br.com.gisa.associados.model.Associado;
import br.com.gisa.associados.enums.SituacaoAssociadoEnum;
import br.com.gisa.associados.model.ExameConsulta;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssociadoDTO extends RepresentationModel<AssociadoDTO> {

    private Long id;

    @NotNull(message = "O nome não pode ser vazio.")
    private String nome;

    @NotNull(message = "O CPF não pode ser vazio.")
    private String cpf;

    @NotNull(message = "O e-mail não pode ser vazio.")
    private String email;

    private Date dataNascimento;

    @NotNull(message = "O Celular não pode ser vazio.")
    private String celular;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String numero;
    private String uf;
    private String complemento;
    private String cep;
    private String localizacao;
    private SituacaoAssociadoEnum situacao;

    @NotNull(message = "O tipo de plano precisa ser informado.")
    private TipoPlanoEnum tipoPlano;
    private Date dataCadastro;
    private Date dataAtualizacao;

    private List<ExameConsulta> historico;

    public Associado convertDTOToEntity() {
        return new ModelMapper().map(this, Associado.class);
    }
}
