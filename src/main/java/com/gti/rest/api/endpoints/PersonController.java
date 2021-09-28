package com.gti.rest.api.endpoints;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gti.rest.api.services.PersonService;
import com.gti.rest.api.ui.models.dto.PersonDTO;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/persons")
@AllArgsConstructor
public class PersonController {
	
	
	private PersonService personService;
	


	@PostMapping
	public PersonDTO savePerson(@Valid @RequestBody PersonDTO personDTO) {
		return personService.savePerson(personDTO);
	}
	
	@GetMapping("/{id}")
	public PersonDTO getPersonById(@PathVariable("id") Long id) {
		return personService.getPerson(id);
	}
	

}
