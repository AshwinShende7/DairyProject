package com.dairy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.dairy.model.LogIn;

@Repository
@EnableJpaRepositories
public interface LogInRepo extends JpaRepository<LogIn, Long> {

	
	LogIn findByusername(String username);

	LogIn findByUsername(String username);

}
