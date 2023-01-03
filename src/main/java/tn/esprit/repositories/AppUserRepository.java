package tn.esprit.repositories;

import org.springframework.data.repository.CrudRepository;
import tn.esprit.entities.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

	//using the keyword query method to retrieve the user by username
	public AppUser findByUsername(String username);


}
