package com.gti.rest.api.db.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.ToString;


@Entity
@Data
@ToString(exclude = "persons")
public class AddressEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(unique = true)
	private String name;
	
	
	@OneToMany(mappedBy = "address")
	private List<PersonEntity> persons;
	
	

}
