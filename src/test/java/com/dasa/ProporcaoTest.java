package com.dasa;

import com.dasa.domain.ProporcaoCampanha;
import com.dasa.utils.CalculaProporcao;
import org.junit.Assert;
import org.junit.Test;

import com.dasa.domain.DadoPopulacional;

/**
 * Created by Cristiano on 2/28/17.
 */
public class ProporcaoTest {

	private final DadoPopulacional d2016 = new DadoPopulacional("2016", "206081432", "101726102", "104355330");

	@Test
	public void deveRetornarAProporcaoDaCampanhaDe2016() {

		ProporcaoCampanha proporcao = new CalculaProporcao(d2016).calcular();

		Assert.assertEquals("2016", proporcao.getCampanha());
		Assert.assertEquals("49.4%", proporcao.getHomens());
		Assert.assertEquals("50.6%", proporcao.getMulheres());
	}


}
