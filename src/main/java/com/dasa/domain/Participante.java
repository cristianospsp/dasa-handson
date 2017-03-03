package com.dasa.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Cristiano on 3/2/17.
 */
@Data
@Entity
public class Participante implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Length(max = 128)
	@NotBlank
	private String nome;

	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	private String ano;

	@Length(max = 128)
	@NotBlank
	private String email;

	public Participante() {
	}

	public Participante(String nome, String sexo, String ano, String email) {
		this.nome = nome;
		this.sexo = Sexo.valueOf(sexo);
		this.ano = ano;
	}

}
