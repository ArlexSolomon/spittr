package spittr.role.service;

import spittr.role.domain.Spitter;

public interface SpitterService {

	Spitter create(Spitter spitter);

	Spitter findByUserName(String username);
}
