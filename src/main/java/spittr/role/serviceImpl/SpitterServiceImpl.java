package spittr.role.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spittr.role.domain.Spitter;
import spittr.role.entity.SpitterEntity;
import spittr.role.repositories.SpitterRepository;
import spittr.role.service.SpitterService;

@Service
public class SpitterServiceImpl implements SpitterService {

	@Autowired
	private SpitterRepository spitterRepository;

	@Override
	public Spitter create(Spitter spitter) {
		SpitterEntity spitterEntity = new SpitterEntity();
		spitterEntity.setUserName(spitter.getUserName());
		spitterEntity.setName(spitter.getName());
		spitterEntity.setPassword(spitter.getPassword());
		spitterRepository.save(spitterEntity);
		spitter.setId(spitterEntity.getId());
		return spitter;
	}

	@Override
	public Spitter findByUserName(String userName) {
		SpitterEntity spitterEntity = spitterRepository.findByUserName(userName);
		if (spitterEntity != null) {
			return buildSpitter(spitterEntity);
		}
		return null;
	}

	public Spitter buildSpitter(SpitterEntity spitterEntity) {
		return new Spitter(spitterEntity.getId(), spitterEntity.getUserName(), spitterEntity.getName(),
				spitterEntity.getPassword());
	}

}
