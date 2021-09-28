package com.gti.rest.api.services;

import com.gti.rest.api.ui.models.dto.PersonDTO;

public interface PersonService {
	
	PersonDTO savePerson(PersonDTO dto);
	PersonDTO getPerson(Long id);

}
