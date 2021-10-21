package com.spring.board.dto;

import java.util.List;

import com.spring.board.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
	private Long id;			// pk			
	private String username;	// 회원ID
	private String password;	// 비밀번호
	private boolean enabled;	// 활성화된 사용자 여부
	private List<String> roles;
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.enabled = user.isEnabled();
	}
}
