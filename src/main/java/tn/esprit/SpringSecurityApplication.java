package tn.esprit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tn.esprit.entities.AppUser;
import tn.esprit.entities.Role;
import tn.esprit.repositories.RoleRepository;
import tn.esprit.services.IAppUserService;

@SpringBootApplication
public class SpringSecurityApplication implements CommandLineRunner{

	@Autowired
	IAppUserService appUserService; 
	
	@Autowired
	RoleRepository roleRepository ;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		
//		Role role1 = new Role().builder().nom("ROLE_USER").build();
//		Role role2 = new Role().builder().nom("ROLE_ADMIN").build();
//
//		Role role11 = roleRepository.save(role1);
//		Role role22 = roleRepository.save(role2);
	
//		AppUser  AppUser1 = AppUser.builder()
//				.nom("user")
//				.username("user")
//				.password("user123")
//				.build();
//
//		AppUser  AppUser2 = AppUser.builder()
//				.nom("admin")
//				.username("admin")
//				.password("admin123")
//				.build();
//		
//		AppUser  AppUser3 = AppUser.builder()
//				.nom("mixteUser")
//				.username("mixteUser")
//				.password("mixteUser123")
//				.build();
//
//
//		
//		Role role11 = roleRepository.findById(1).orElse(null);
//		Role role22 = roleRepository.findById(2).orElse(null);
//		AppUser1.getRoles().add(role11);
//		AppUser2.getRoles().add(role22);
//		AppUser3.getRoles().add(role11);
//		AppUser3.getRoles().add(role22);

	
//		
//		AppUser  AppUser4 = appUserService.retriveUserByName("user");
//		role11.getAppUsers().add(AppUser4);
//		role22.getAppUsers().add(AppUser4);
//		roleRepository.save(role11);
//		roleRepository.save(role22);
		
//		appUserService.register(AppUser1);
//		appUserService.register(AppUser2);
//		appUserService.register(AppUser3);
	}

}
