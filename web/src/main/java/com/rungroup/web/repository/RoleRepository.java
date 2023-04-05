package com.rungroup.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rungroup.web.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByName(String name);
}
