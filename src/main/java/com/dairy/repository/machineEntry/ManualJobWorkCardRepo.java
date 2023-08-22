package com.dairy.repository.machineEntry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dairy.model.machineEntry.ManualJobWorkCard;



@Repository
@EnableJpaRepositories
public interface ManualJobWorkCardRepo extends JpaRepository<ManualJobWorkCard, Integer> {

	@Transactional
	int deleteManualJobWorkCardById(int id);

	@Transactional
	int deleteByid(int id);

}
