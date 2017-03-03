package com.dasa.utils;

import com.dasa.domain.DadoPopulacional;

import java.math.BigDecimal;

/**
 * Created by Cristiano on 2/28/17.
 */
public class Estimativa {

	private final Integer ano;
	private final DadoPopulacional pop1;
	private final DadoPopulacional pop2;

	public Estimativa(Integer ano, DadoPopulacional pop1, DadoPopulacional pop2) {
		this.ano = ano;
		this.pop1 = pop1;
		this.pop2 = pop2;
	}

	public DadoPopulacional estimar() {

		int indice = Integer.valueOf(pop1.getAno()) - Integer.valueOf(pop2.getAno());

		BigDecimal totalEstimadoHomens = ajustaCasasDecimaais(7, Math.pow((pop2.getTotalHomens() / pop1.getTotalHomens()), (1 / indice)) * pop2.getTotalHomens());
		BigDecimal totalEstimadoMulheres = ajustaCasasDecimaais(7, Math.pow((pop2.getTotalMulheres() / pop1.getTotalMulheres()), (1 / indice)) * pop2.getTotalMulheres());
		BigDecimal soma = totalEstimadoHomens.add(totalEstimadoMulheres);
		String totalEstimado = ajustaCasasDecimaais(0, soma.doubleValue()).toString();

		return new DadoPopulacional(String.valueOf(ano), totalEstimado,
			 String.valueOf(ajustaCasasDecimaais(0, totalEstimadoHomens.doubleValue())), String.valueOf(ajustaCasasDecimaais(0, totalEstimadoMulheres.doubleValue())));
	}

	private BigDecimal ajustaCasasDecimaais(int casasDecimais, Double valor) {
		return new BigDecimal(valor).setScale(casasDecimais, BigDecimal.ROUND_HALF_UP);
	}

}
