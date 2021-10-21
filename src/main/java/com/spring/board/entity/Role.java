package com.spring.board.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Entity
@Data
public class Role implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	@ManyToMany
	@JoinTable(
	  name = "user_role",
	  joinColumns = @JoinColumn(name = "role_id"),
	  inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> users;
	
	@Override
	public String getAuthority() {
		return name;
	}
	
}
