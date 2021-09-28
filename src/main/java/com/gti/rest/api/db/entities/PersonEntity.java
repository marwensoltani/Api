package com.gti.rest.api.db.entities;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "address")
public class PersonEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "person_name", length = 45, nullable = false, unique = true)
	private String name;
	private LocalDate dateOfBirth;
	@Column(name = "person_nickname", length = 45, nullable = true)
	private String nickName;

	public int getAge() {
		LocalDate now = LocalDate.now();
		 return Period.between(dateOfBirth,now).getYears();
	}
	
	

	public PersonEntity(Long id, String name, LocalDate dateOfBirth, String nickName) {
		super();
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.nickName = nickName;
	}



	@ManyToOne
	private AddressEntity address;
	
}