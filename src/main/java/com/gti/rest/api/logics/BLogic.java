package com.gti.rest.api.logics;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Collection;
import static java.util.Comparator.*;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gti.rest.api.db.entities.PersonEntity;
import com.gti.rest.api.db.repos.PersonRepository;
import com.gti.rest.api.models.Person;
import com.gti.rest.api.tools.FileTools;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping
public class BLogic {

	private PersonRepository personRepository;
	private ModelMapper mapper;
	private FileTools fileTools;

	public Map<String, List<Person>> personsOverWhat(Predicate<Person> whatCondition) {

		return ((Collection<PersonEntity>) personRepository.findAll()).stream().map(pe -> mapper.map(pe, Person.class))
				.filter(whatCondition).collect(Collectors.groupingBy(p -> p.getAddress().getName()));

	}

	@GetMapping("/test/{age}")
	public Map<String, List<Person>> personsOverAge(@PathVariable("age") int age) {
		return personsOverWhat(p -> p.getAge() > age);
	}

	@GetMapping("/test/read/{txt}")
	public byte[] readAndEncode64(@PathVariable("txt") String fileName) {

		try {
			return Base64.getEncoder().encode(fileTools.readSource(fileName).getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@PostMapping("/test/read")
	public String readAndDencode64(@RequestBody String fileName) {
		return new String(Base64.getDecoder().decode(fileName));

	}

	@GetMapping("/q1")
	public double personsAverageAges() {

		return ((Collection<PersonEntity>) personRepository.findAll()).stream().mapToInt(pe -> pe.getAge()).average()
				.orElse(0);

	}

	@GetMapping("/q2/age/{age}")
	public long personsCountByAge(@PathVariable("age") int age) {
		return ((Collection<PersonEntity>) personRepository.findAll()).stream().mapToInt(pe -> pe.getAge())
				.filter(a -> a == age).count();

	}

	@GetMapping("/q3/")
	public List<Person> personsBornInThisDay() {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("ddMM");

		String thisDay = LocalDate.now().format(pattern);

		return ((Collection<PersonEntity>) personRepository.findAll()).stream()
				.filter(pe -> pe.getDateOfBirth().format(pattern).equals(thisDay))
				.map(pe -> mapper.map(pe, Person.class))
				.collect(Collectors.toList());

	}

	@GetMapping("/q4/month/{month}")
	public List<Person> personsBornInSpecificMonth(@PathVariable("month") int month) {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM");

		if (!(month <= 12 && month >= 1))
			throw new IllegalArgumentException("month must be between 1 and 12");
		return ((Collection<PersonEntity>) personRepository.findAll()).stream()
				.filter(pe -> Integer.parseInt(pe.getDateOfBirth().format(pattern)) == month)
				.map(pe -> mapper.map(pe, Person.class)).collect(Collectors.toList());

	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArg(IllegalArgumentException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/q5/")
	public Map<Boolean, List<Person>> adultsPersons() {
		return ((Collection<PersonEntity>) personRepository.findAll()).stream()
														   .map(pe -> mapper.map(pe, Person.class))
														   .collect(Collectors.partitioningBy(p->p.getAge()>18));
	}

	@GetMapping("/q6/")
	public Map<Integer, List<Person>> personsByYearOfBirth() {

		return ((Collection<PersonEntity>) personRepository.findAll()).stream()
				   .map(pe -> mapper.map(pe, Person.class))
				   .collect(Collectors.groupingBy(p-> p.getDateOfBirth().getYear()));
	}

	@GetMapping("/q7/")
	public Map<String, List<Person>> personsByNickName() {

		return ((Collection<PersonEntity>) personRepository.findAll()).stream()
				   .map(pe -> mapper.map(pe, Person.class))
				   .sorted(comparing(Person::getName).thenComparing(p->p.getAge()))
				   .collect(Collectors.groupingBy(p-> p.getNickName()!=null?p.getNickName():"missing"));
	}

}
