package tn.esprit.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.entities.AppUser;
import tn.esprit.entities.Role;
import tn.esprit.repositories.AppUserRepository;

@Service
public class IAppUserServiceImpl implements IAppUserService {

	@Autowired
	PasswordEncoder passwordEncoder ;

	@Autowired
	AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//		 la premiere étape est de chercher si l'utilisateur existe dans la base ou non
		AppUser appUser = appUserRepository.findByUsername(username);
		
		if(appUser == null) throw new UsernameNotFoundException(username);

		return new User(appUser.getUsername(),appUser.getPassword(),getAuthorities(appUser.getRoles()));
	}

	private List<GrantedAuthority> getAuthorities(List<Role> userRoles) {
				
		List<GrantedAuthority> grantedAuthorities = userRoles.stream()
		.map(role -> new SimpleGrantedAuthority(role.getNom()))
		.collect(Collectors.toList());
		
//		 ces 3 lignes ci-dessus de codes sont équivalants à ce logique:
//		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
//		for (Role role : userRoles) {
//			roles.add(new SimpleGrantedAuthority(role.getNom()));      
//		}
		System.out.println("hello");
		grantedAuthorities.stream().forEach(r -> System.out.println(r));
		return grantedAuthorities;    
	}


	@Override
	public AppUser register(AppUser appUser) {
		String pwdCrypted  = passwordEncoder.encode(appUser.getPassword());
		appUser.setPassword(pwdCrypted);
		return appUserRepository.save(appUser);
	}

	@Override
	public AppUser retriveUserByName(String name) {
		// TODO Auto-generated method stub
		return appUserRepository.findByUsername(name);
	}

}
