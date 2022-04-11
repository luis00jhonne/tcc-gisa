package br.com.gisa.associados.dto.model;

import br.com.gisa.associados.model.Associado;
import br.com.gisa.associados.model.ExameConsulta;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExameConsultaDTO extends RepresentationModel<ExameConsultaDTO> {

    private String codigo;
    private String nome;

    public ExameConsulta convertDTOToEntity() {
        return new ModelMapper().map(this, ExameConsulta.class);
    }
}
