package tn.esprit.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nom;
	private String prenom;
	private String username;
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "appUsers")
	private List<Role> roles;

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", username=" + username + ", password="
				+ password + ", roles=" + roles + "]";
	}
	
	

}
