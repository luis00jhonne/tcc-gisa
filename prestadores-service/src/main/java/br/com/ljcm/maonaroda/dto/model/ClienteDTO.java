package br.com.ljcm.maonaroda.dto.model;

import br.com.ljcm.maonaroda.enums.SituacaoCadastroEnum;
import br.com.ljcm.maonaroda.model.Cliente;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO extends RepresentationModel<ClienteDTO> {

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
    private SituacaoCadastroEnum situacao;
    private Date dataCadastro;
    private Date dataAtualizacao;

    public Cliente convertDTOToEntity() {
        return new ModelMapper().map(this, Cliente.class);
    }
}
