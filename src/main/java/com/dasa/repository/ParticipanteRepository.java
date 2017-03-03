package com.dasa.repository;

import com.dasa.domain.Participante;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by Cristiano on 3/2/17.
 */

@Transactional
public interface ParticipanteRepository extends CrudRepository<Participante, Long> {

}
