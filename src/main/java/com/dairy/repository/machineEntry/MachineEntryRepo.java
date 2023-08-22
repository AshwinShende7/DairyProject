package com.dairy.repository.machineEntry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dairy.model.machineEntry.MachineMaster;



@Repository
@EnableJpaRepositories
public interface MachineEntryRepo extends JpaRepository<MachineMaster, Integer>{

	@Transactional
	int deleteMachineMasterById(int id);

	@Transactional
	int deleteByid(int id);

}
