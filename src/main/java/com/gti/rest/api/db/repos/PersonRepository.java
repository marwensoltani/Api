package com.gti.rest.api.db.repos;

import org.springframework.data.repository.CrudRepository;

import com.gti.rest.api.db.entities.PersonEntity;


public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

}
