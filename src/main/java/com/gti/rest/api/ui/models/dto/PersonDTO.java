package com.gti.rest.api.ui.models.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.gti.rest.api.tools.DateTools;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDTO {

	@NotNull(message = "Peson should have a name.")
	private String name;
	@Past(message = "Date of birth must be in the past.")
	private LocalDate dateOfBirth;
	
	private AddressDTO address;

	public void setDateOfBirth(String date) {
		this.dateOfBirth = DateTools.convertDate(date);
	}

	public void setDateOfBirth(LocalDate date) {
		this.dateOfBirth = date;
	}

	public PersonDTO(@NotNull(message = "Peson should have a name.") String name,
			@Past(message = "Date of birth must be in the past.") LocalDate dateOfBirth) {
		super();
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}
	
	

}
