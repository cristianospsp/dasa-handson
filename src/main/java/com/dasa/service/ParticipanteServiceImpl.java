package com.dasa.service;

import com.dasa.domain.Participante;
import com.dasa.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Cristiano on 3/2/17.
 */
@Service
public class ParticipanteServiceImpl implements ParticipanteService {

	@Autowired
	private ParticipanteRepository repository;

	@Override
	public Participante insert(Participante usuario) {
		return repository.save(usuario);
	}

}
