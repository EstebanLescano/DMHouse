package org.esteban.lescano.dmhouse.repository;

import org.esteban.lescano.dmhouse.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Integer> {

	Client findByEmail (String email);
}


