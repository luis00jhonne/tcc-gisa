package br.com.gisa.associados.dto.model;

import br.com.gisa.associados.model.ExameConsulta;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExameConsultaDTO extends RepresentationModel<ExameConsultaDTO> {

    @NotNull(message = "O código do exame/consulta deve ser informado.")
    private String codigo;
    private String nome;

    public ExameConsulta convertDTOToEntity() {
        return new ModelMapper().map(this, ExameConsulta.class);
    }
}
