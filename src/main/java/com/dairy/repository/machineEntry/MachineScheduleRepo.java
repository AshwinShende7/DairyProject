package com.dairy.repository.machineEntry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dairy.model.machineEntry.MachineScheduler;

@Repository
@EnableJpaRepositories
public interface MachineScheduleRepo  extends JpaRepository<MachineScheduler, Integer>{
   


}
