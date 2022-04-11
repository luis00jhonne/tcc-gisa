package br.com.gisa.associados.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table
@Entity(name="exame_consulta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExameConsulta {

    private @Id String codigo;
    private String descricao;
}
