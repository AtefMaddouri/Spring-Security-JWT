package tn.esprit;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import tn.esprit.entities.AppUser;
import tn.esprit.entities.Role;
import tn.esprit.repositories.AppUserRepository;
import tn.esprit.repositories.RoleRepository;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringSecurityApplication implements CommandLineRunner{

	private final RoleRepository roleRepository;
	private final AppUserRepository appUserRepository;
	@Value("${app.user.admin.username}")
	String adminUsername;
	@Value("${app.user.admin.pwd}")
	String adminPWD;

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		if(appUserRepository.findByUsername(adminUsername) == null) {
			Role role = new Role().builder().roleName("ROLE_ADMIN").build();
			AppUser appAdmin = AppUser.builder()
					.nom("Admin")
					.username(adminUsername)
					.password(passwordEncoder().encode(adminPWD))
					.role(roleRepository.save(role))
					.build();
			appUserRepository.save(appAdmin);
		}
	}

}
