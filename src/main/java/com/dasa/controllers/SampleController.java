package com.dasa.controllers;

import com.dasa.domain.DadoPopulacional;
import com.dasa.domain.Participante;
import com.dasa.service.DadosPopulacionaisService;
import com.dasa.service.ParticipanteService;
import com.dasa.utils.CalculaProporcao;
import com.dasa.utils.Estimativa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.dasa.domain.Resposta.*;


@RestController
public class SampleController {

	@Autowired
	DadosPopulacionaisService service;

	@Autowired
	ParticipanteService participanteService;

	@RequestMapping("/hello")
	public String helloWorld() {
		return "Hello =)";
	}

	@RequestMapping("/2010")
	public ResponseEntity<?> get2010data() {
		Optional<DadoPopulacional> dadoOpt = Optional.ofNullable(service.obterPopulacaoPorAno(Optional.of("2010")));
		if (!dadoOpt.isPresent()) {
			return ResponseEntity.ok(NAO_EXISTEM_DADOS);
		}
		return ResponseEntity.ok(dadoOpt.get());
	}

	@RequestMapping(value = "/dadosDaCampanha/{ano}")
	public ResponseEntity<?> getDadosPorAno(@PathVariable String ano) {
		Optional<DadoPopulacional> dadoOpt = Optional.ofNullable(service.obterPopulacaoPorAno(Optional.of(ano)));
		if (!dadoOpt.isPresent()) {
			return ResponseEntity.ok(DADOS_NAO_ENCONTRADO_PARA_O_ANO_REQUISITADO);
		}
		return ResponseEntity.ok(dadoOpt.get());
	}

	@RequestMapping(value = "/proporcaoDaCampanha/{ano}")
	public ResponseEntity<?> getProporcaoDaCampanha(@PathVariable String ano) {
		Optional<DadoPopulacional> dadoOpt = Optional.ofNullable(service.obterPopulacaoPorAno(Optional.of(ano)));
		if (!dadoOpt.isPresent()) {
			return ResponseEntity.ok(DADOS_NAO_ENCONTRADO_PARA_O_ANO_REQUISITADO);
		}
		return ResponseEntity.ok(new CalculaProporcao(dadoOpt.get()).calcular());
	}

	@RequestMapping(value = "/estimadoPara/{ano}")
	public ResponseEntity<?> getDadosEstimado(@PathVariable String ano) {
		Optional<List<DadoPopulacional>> allOpt = Optional.ofNullable(service.findAll());

		if (!allOpt.isPresent()) {
			return ResponseEntity.ok(NAO_EXISTEM_DADOS);
		}

		List<DadoPopulacional> all = allOpt.get();

		DadoPopulacional d1 = all.stream().min((a, b) -> a.getAno().compareTo(b.getAno())).get();
		DadoPopulacional d2 = all.stream().max((a, b) -> a.getAno().compareTo(b.getAno())).get();

		if (periodoValido(ano, d2)) {
	  	return ResponseEntity.ok(IMPOSSIVEL_ESTIMAR_PARA_CAMPANHA_INFORMADA);
		}

		return ResponseEntity.ok(new Estimativa(Integer.valueOf(ano), d1, d2).estimar());
	}

	private boolean periodoValido(String ano, DadoPopulacional d2) {
		return Integer.valueOf(ano) - (Integer.valueOf(d2.getAno()) + 1) != 0;
	}

	@RequestMapping(value = "/registra", method = RequestMethod.POST)
	public ResponseEntity<?> registraParticipante(@RequestBody Participante participante) {
		return ResponseEntity.ok(participanteService.insert(participante));
	}


}
