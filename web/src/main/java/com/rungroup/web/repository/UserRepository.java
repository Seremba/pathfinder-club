package com.rungroup.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rungroup.web.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	
	UserEntity findByEmail(String email);
	
	UserEntity findByUsername(String username);
	
	UserEntity findFirstByUsername(String username);
}
