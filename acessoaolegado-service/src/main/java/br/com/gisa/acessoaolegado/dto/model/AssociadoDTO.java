package br.com.gisa.acessoaolegado.dto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AssociadoDTO extends RepresentationModel<AssociadoDTO> {

    private Long id;

    @NotNull(message = "O CPF não pode ser vazio.")
    private String cpf;
}
