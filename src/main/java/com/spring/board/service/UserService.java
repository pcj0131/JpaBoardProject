package com.spring.board.service;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.board.dto.UserDTO;
import com.spring.board.entity.Role;
import com.spring.board.entity.User;
import com.spring.board.repostitory.RoleRepository;
import com.spring.board.repostitory.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private SecurityService securityService;
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, RoleRepository roleRepository, SecurityService securityService, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.securityService = securityService;
		this.passwordEncoder = passwordEncoder;
	}
	
	public void saveUser(UserDTO userDto) {
		User user = new User();
		
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEnabled(true);
		
		Role role = roleRepository.findByName("ROLE_USER");
		user.setRoles(Arrays.asList(role));
		userDto.setRoles(Arrays.asList(role.getName()));
		
		userRepository.save(user);
	}
	
	public boolean login(UserDTO userDto) {
		return securityService.login(userDto.getUsername(), userDto.getPassword());
	}
}
