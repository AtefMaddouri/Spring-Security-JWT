package tn.esprit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.entities.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

//	//une méthode en utilisant keywords de spring data Jpa(voir cours SPRING DATA JPA (CrudRepository) page 7)
	public AppUser findByUsername(String username);


}
