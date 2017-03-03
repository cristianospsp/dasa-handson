package com.dasa;

import com.dasa.domain.DadoPopulacional;
import com.dasa.utils.Estimativa;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Cristiano on 2/28/17.
 */
public class EstimativaCalcTest {

	private final DadoPopulacional d2000 = new DadoPopulacional("2000", "173448346", "86169657", "87278689");
	private final DadoPopulacional d2016 = new DadoPopulacional("2016", "206081432", "101726102", "104355330");

	@Test
	public void deveEstimarPopulacaoPara2017() {
		DadoPopulacional estimadoPara2017 = new Estimativa(2017, d2000, d2016).estimar();

		Assert.assertEquals("2017", estimadoPara2017.getAno());
		Assert.assertEquals(new BigDecimal("206081432"), estimadoPara2017.getPopulacaoTotal());
		Assert.assertEquals(Long.valueOf("101726102"), estimadoPara2017.getTotalHomens());
		Assert.assertEquals(Long.valueOf("104355330"), estimadoPara2017.getTotalMulheres());

	}

}
