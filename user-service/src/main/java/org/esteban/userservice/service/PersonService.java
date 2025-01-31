package org.esteban.userservice.service;

import org.esteban.userservice.entity.Person;
import org.esteban.userservice.models.ClientDTO;
import org.esteban.userservice.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void savePerson (Person person) {
	personRepository.save(person);
}

    public Person createPerson(ClientDTO dto) {
        Person person = new Person();
        person.setName(dto.getName());
        person.setLastName(dto.getLastName());
        person.setEmail(dto.getEmail());
        return person;
    }

    public boolean registerUser(ClientDTO dto) {
        Person person = createPerson(dto);
        savePerson(person);
        return true;
    }

    public Optional<Person> getPersonById(Integer id) {
    return personRepository.findByClientId(id);
    }
}