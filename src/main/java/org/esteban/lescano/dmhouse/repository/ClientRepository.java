package org.esteban.lescano.dmhouse.repository;

import org.esteban.lescano.dmhouse.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client, Integer> {

	Optional<Client> findByEmail (String email);


}


