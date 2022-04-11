package br.com.gisa.associados.dto.model;

import br.com.gisa.associados.enums.SituacaoAutorizacaoEnum;
import br.com.gisa.associados.model.Associado;
import br.com.gisa.associados.model.AutorizacaoExameConsulta;
import br.com.gisa.associados.model.ExameConsulta;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AutorizacaoExameConsultaDTO extends RepresentationModel<AutorizacaoExameConsultaDTO> {

    private Long id;

    @NotNull(message = "O associado deve ser informado.")
    private Associado associado;

    @NotNull(message = "O exame/consulta deve ser informado.")
    private ExameConsultaDTO exameConsulta;

    private String codigoAutorizacao;

    private Date dataSolicitacao;
    private Date dataAutorizacao;
    private Date dataRealizacao;

    private SituacaoAutorizacaoEnum situacao;

    public AutorizacaoExameConsulta convertDTOToEntity() {
        return new ModelMapper().map(this, AutorizacaoExameConsulta.class);
    }
}
