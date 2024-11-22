package org.esteban.lescano.dmhouse.Services;

import org.esteban.lescano.dmhouse.Repository.PersonRepository;
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