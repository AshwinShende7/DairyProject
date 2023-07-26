package com.dairy.repository.sansthaMaster;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.dairy.model.sansthaMaster.SansthaMaster;


@EnableJpaRepositories
@Repository
public interface SansthaMasterRepo  extends JpaRepository<SansthaMaster, Integer>
{
	@Transactional
     int deleteByid(int id);
}
