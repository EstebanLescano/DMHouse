package org.esteban.lescano.dmhouse.services;

import org.esteban.lescano.dmhouse.repository.PersonRepository;
import org.esteban.lescano.dmhouse.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
@Autowired
PersonRepository personRepository;

public void savePerson (Person person) {
	personRepository.save(person);
}

}