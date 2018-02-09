package spittr.role.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import spittr.role.entity.SpitterEntity;

public interface SpitterRepository
		extends PagingAndSortingRepository<SpitterEntity, Long>, JpaSpecificationExecutor<SpitterEntity> {

	SpitterEntity findByUserName(String userName);

}
