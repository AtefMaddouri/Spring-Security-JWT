package tn.esprit.services;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UserDetailsService;

import tn.esprit.entities.AppUser;

public interface IAppUserService extends UserDetailsService{

	public AppUser register(AppUser appUser);
	public AppUser retriveUserByName(String name);
}
