package com.dasa.utils;

import com.dasa.domain.DadoPopulacional;
import com.dasa.domain.ProporcaoCampanha;

/**
 * Created by Cristiano on 2/28/17.
 */
public class CalculaProporcao {

	private final DadoPopulacional dadoPopulacional;

	public CalculaProporcao(DadoPopulacional campanha) {
		this.dadoPopulacional = campanha;
	}

	public ProporcaoCampanha calcular() {

		float h = (Float.valueOf(dadoPopulacional.getTotalHomens()) / dadoPopulacional.getPopulacaoTotal().floatValue()) * 100;
		float m = (Float.valueOf(dadoPopulacional.getTotalMulheres()) / dadoPopulacional.getPopulacaoTotal().floatValue()) * 100;

		return new ProporcaoCampanha(dadoPopulacional.getAno(), h, m);
	}
}
