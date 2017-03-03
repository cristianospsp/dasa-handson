package com.dasa.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Cristiano on 3/2/17.
 */
@Data
public class ProporcaoCampanha implements Serializable {

	private final String campanha;
	private final String homens;
	private final String mulheres;

	public ProporcaoCampanha(String campanha, Float homens, Float mulheres) {
		this.campanha = campanha;
		this.homens = String.format("%.1f", homens).concat("%");
		this.mulheres = String.format("%.1f", mulheres).concat("%");
	}

}
