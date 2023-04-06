package com.rungroup.web.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.rungroup.web.dto.RegistrationDto;
import com.rungroup.web.models.UserEntity;
import com.rungroup.web.service.UserService;

@Controller
public class AuthController {
	private UserService userService;

	public AuthController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/register")
	public String getRegisterForm(Model model) {
		RegistrationDto user = new RegistrationDto();
		model.addAttribute("user", user);
		return "register";
	}
	
	@PostMapping("/register/save")
	public String register(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result, Model model) {
		
		UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
		if (existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
			result.rejectValue("email", "There is already a user with this username/email");
			return "redirect:/register?fail";
		}
		
		UserEntity existingUsername = userService.findByUsername(user.getUsername());
		
		if (existingUsername != null && existingUsername.getUsername() != null
		        && !existingUsername.getUsername().isEmpty()) {
			result.rejectValue("username", "There is already a user with this username/email");
			return "redirect:/register?fail";
		}
		
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "register";
		}
		
		userService.saveUser(user);
		
		return "redirect:/clubs?success";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
}
