package com.spring.board.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = "roles") 
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;	// 회원ID
	private String password;	// 비밀번호
	private boolean enabled;	// 활성화된 사용자 여부
	
	@ManyToMany
	@JoinTable(
	  name = "user_role",
	  joinColumns = @JoinColumn(name = "user_id"),
	  inverseJoinColumns = @JoinColumn(name ="role_id")
	)
	private List<Role> roles = new ArrayList<>();
			
	@Builder
	public User(Long id, String username, String password, boolean enabled, String roleName) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
}
