package org.esteban.lescano.dmhouse.Repository;

import org.esteban.lescano.dmhouse.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
