package com.spring.board.repostitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.board.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query("from Role where name=:name")
	Role findByName(@Param("name")String name);
}
