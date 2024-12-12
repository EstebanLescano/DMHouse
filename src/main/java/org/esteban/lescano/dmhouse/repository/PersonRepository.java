package org.esteban.lescano.dmhouse.repository;

import org.esteban.lescano.dmhouse.entities.Client;
import org.esteban.lescano.dmhouse.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByDni(String dni);

    Optional<Client> findByEmail(String email);

}
