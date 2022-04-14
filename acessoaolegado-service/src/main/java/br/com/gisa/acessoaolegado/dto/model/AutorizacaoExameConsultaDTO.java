package br.com.gisa.acessoaolegado.dto.model;

import br.com.gisa.acessoaolegado.enums.SituacaoAutorizacaoEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AutorizacaoExameConsultaDTO extends RepresentationModel<AutorizacaoExameConsultaDTO> {

    @NotNull(message = "O associado deve ser informado.")
    private AssociadoDTO associado;

    @NotNull(message = "O exame/consulta deve ser informado.")
    private ExameConsultaDTO exameConsulta;

    private String codigoAutorizacao;

    private Date dataSolicitacao;
    private Date dataAutorizacao;
    private Date dataRealizacao;

    private SituacaoAutorizacaoEnum situacao;
}
