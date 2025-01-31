package org.esteban.userservice.repository;

import org.esteban.userservice.entity.Client;
import org.esteban.userservice.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByDni(String dni);

    Optional<Person> findByClientId(Integer id);


}
