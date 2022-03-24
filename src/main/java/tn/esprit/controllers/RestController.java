package tn.esprit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.entities.AppUser;
import tn.esprit.services.IAppUserService;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	IAppUserService userService;

	@GetMapping("admin/sayHello")
	public String SayHelloToAdmin() {
		return "Hello Admin";
	}
	
	@GetMapping("user/sayHello")
	public String SayHelloToUser() {
		return "Hello User";
	}
	

//	@Secured("ROLE_USER")
	@PreAuthorize("hasRole('USER')")
	@GetMapping("hello/{username}")
	public AppUser securedGetUserByUserName(@PathVariable String username) {
		return userService.retriveUserByName(username);
	}
	
//	@GetMapping("access/{username}")
//	public AppUser getUserByUserName(@PathVariable String username) {
//		return userService.retriveUserByName(username);
//	}

	
	
}
