package org.esteban.lescano.dmhouse.services;

import org.esteban.lescano.dmhouse.models.ClientDTO;
import org.esteban.lescano.dmhouse.repository.PersonRepository;
import org.esteban.lescano.dmhouse.entities.Person;
import org.springframework.stereotype.Service;

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
}