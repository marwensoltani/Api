package com.gti.rest.api.endpoints;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.gti.rest.api.endpoints.errors.HandlePersonsException;
import com.gti.rest.api.services.PersonService;
import com.gti.rest.api.ui.models.dto.PersonDTO;

class PersonControllerTest {
	@InjectMocks
	PersonController personController;
	@Mock
	PersonService personService;
	
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
       
        mockMvc = MockMvcBuilders.standaloneSetup(personController)
        		.setControllerAdvice(new HandlePersonsException())
        		.build();
		
	}

	@Test
	void getPersonByIdNotFound() throws Exception {
		when(personService.getPerson(anyLong())).thenThrow(NoSuchElementException.class);
		
		mockMvc.perform(get("/api/persons/-1"))
			 .andExpect(status().isNotFound());
	}
	
	@Test
	void getPersonByIdBadRequest() throws Exception {
		when(personService.getPerson(anyLong())).thenThrow(NumberFormatException.class);
		
		mockMvc.perform(get("/api/persons/wrong"))
			 .andExpect(status().isBadRequest());
	}
	
	@Test
	void getPersonById() throws Exception {
		PersonDTO person = new PersonDTO("GTI", LocalDate.of(2000,1,1));
		when(personService.getPerson(anyLong())).thenReturn(person);
		
		mockMvc.perform(get("/api/persons/1"))
			 .andExpect(status().isOk());
	}

}
