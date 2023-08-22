package com.dairy.repository.machineEntry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dairy.model.machineEntry.MachineJobCard;



@Repository
public interface MachineJobCardRepo extends JpaRepository<MachineJobCard, Integer> {

	@Transactional
	int deleteMachineJobCardById(int id);

	@Transactional
	int deleteByid(int id);

}
