package br.com.ljcm.maonaroda.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name="servico")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Servico {
    private @Id @GeneratedValue Long id;
    private String descricao;
    private String especialidade;
    private Cliente cliente;

}
