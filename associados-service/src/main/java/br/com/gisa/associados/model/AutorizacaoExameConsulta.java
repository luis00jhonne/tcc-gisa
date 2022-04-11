package br.com.gisa.associados.model;

import br.com.gisa.associados.dto.model.AutorizacaoExameConsultaDTO;
import br.com.gisa.associados.enums.SituacaoAutorizacaoEnum;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name="autorizacao_exame_consulta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AutorizacaoExameConsulta implements Serializable {

    private @Id @GeneratedValue Long id;

    @ManyToOne
    @JoinColumn(name = "id_associado")
    private Associado associado;

    @ManyToOne
    @JoinColumn(name = "id_exame_consulta")
    private ExameConsulta exameConsulta;

    private String codigoAutorizacao;

    private Date dataSolicitacao;
    private Date dataAutorizacao;
    private Date dataRealizacao;

    private SituacaoAutorizacaoEnum situacao;

    public AutorizacaoExameConsultaDTO convertEntityToDTO() {
        return new ModelMapper().map(this, AutorizacaoExameConsultaDTO.class);
    }
}
