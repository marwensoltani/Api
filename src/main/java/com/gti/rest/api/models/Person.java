package com.gti.rest.api.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Comparable<Person>{
	@NonNull private String name;
	private LocalDate dateOfBirth;
	private String nickName;
	private int age;
	
	private Address address;

	@Override
	public int compareTo(Person o) {
		return this.getAge()-o.getAge();
	}

	
	
	
}
