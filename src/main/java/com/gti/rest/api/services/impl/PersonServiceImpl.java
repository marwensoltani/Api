package com.gti.rest.api.services.impl;

import java.util.NoSuchElementException;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.gti.rest.api.db.entities.PersonEntity;
import com.gti.rest.api.db.repos.PersonRepository;
import com.gti.rest.api.models.Person;
import com.gti.rest.api.services.PersonService;
import com.gti.rest.api.ui.models.dto.PersonDTO;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Service
@AllArgsConstructor
@Setter
public class PersonServiceImpl implements PersonService {
	
	private ModelMapper modelMapper;
	private PersonRepository personRepository;

	@Override
	public PersonDTO savePerson(PersonDTO dto) {
		PersonEntity personEntity = modelMapper.map(dto, PersonEntity.class);
		return modelMapper.map(personRepository.save(personEntity),PersonDTO.class);
	}

	@Override
	public PersonDTO getPerson(Long id) {
		PersonEntity personEntity = personRepository.findById(id)
				.orElseThrow(()-> new NoSuchElementException("Person with this id is not found"));
		
		System.out.println(modelMapper.map(personEntity,Person.class));
		System.out.println(personEntity);
		return  modelMapper.map(personEntity,PersonDTO.class);
	}

}
