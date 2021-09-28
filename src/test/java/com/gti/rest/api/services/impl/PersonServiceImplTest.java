package com.gti.rest.api.services.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.gti.rest.api.db.entities.PersonEntity;
import com.gti.rest.api.db.repos.PersonRepository;
import com.gti.rest.api.ui.models.dto.PersonDTO;

class PersonServiceImplTest {
	
	@InjectMocks
	PersonServiceImpl personService;
	
	@Mock
	PersonRepository personRepository;
	
	@Mock
	ModelMapper modelMapper;
	Optional<PersonEntity> resultDB, resultDBEmpty;
	PersonEntity personEntity;
	PersonDTO personDTO;
	@BeforeEach
	void setUp() throws Exception {
		personEntity = new PersonEntity(1L, "GTI",LocalDate.of(2000,1,1),"gti");
		personDTO = new PersonDTO("GTI", LocalDate.of(2000,1,1));
		resultDB = Optional.of(personEntity);
		resultDBEmpty = Optional.empty();
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetPerson() {
		
		when(modelMapper.map(any(), any())).thenReturn(personDTO);
		
		when(personRepository.findById(anyLong())).thenReturn(resultDB);
		
		assertAll(()->assertEquals(personDTO.getName(), personService.getPerson(1L).getName(),"Should Have the same name"),
				()->assertEquals(personDTO.getDateOfBirth(), personService.getPerson(1L).getDateOfBirth(), "Should have the Same DOB"));
		
		
	}
	
	@Test
	void testGetPersonNotFound() {
		
		when(modelMapper.map(any(), any())).thenReturn(personDTO);
		
		when(personRepository.findById(anyLong())).thenReturn(resultDBEmpty);
		
		assertThrows(NoSuchElementException.class, () -> personService.getPerson(1L));
		
	}

}
