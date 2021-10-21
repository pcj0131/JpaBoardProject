package com.spring.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.board.dto.UserDTO;
import com.spring.board.service.UserService;

@Controller
@RequestMapping("/account")
public class UserController {
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	//private static final Logger Logger = LoggerFactory.getLogger(UserController.class);
	
	// 로그인
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new UserDTO());
		return "account/login";
	}
	
	@PostMapping("/login")
	public String login(UserDTO userDto) {
		userService.login(userDto);
		return "redirect:/";
	}
	
	// 회원가입
	@GetMapping("/register")
	public String register() {
		return "/account/register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute UserDTO userDto, Model model) {
		userService.saveUser(userDto);
		return "redirect:/account/login";
	}
}
