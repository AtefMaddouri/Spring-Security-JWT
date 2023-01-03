package tn.esprit.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import tn.esprit.entities.AppUser;
import tn.esprit.entities.Role;
import tn.esprit.repositories.RoleRepository;
import tn.esprit.services.AppUserService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AppUserService userService;
	private final RoleRepository roleRepository;

	@PostMapping("/register")
	public AppUser register(@RequestBody AppUser appUser){
		return userService.register(appUser);
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/addRole")
	public Role addRole(@RequestBody Role role){
		return roleRepository.save(role);
	}


	@Secured("ROLE_ADMIN")
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("admin/sayHello")
	public String SayHelloToAdmin() {
		return "Hello Admin";
	}


	//	@Secured("ROLE_USER")
	@PreAuthorize("hasRole('USER')")
	@GetMapping("user/sayHello")
	public String SayHelloToUser() {
		return "Hello User";
	}

	

	
	
}
