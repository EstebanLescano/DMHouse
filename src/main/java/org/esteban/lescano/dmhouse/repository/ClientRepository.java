package org.esteban.lescano.dmhouse.repository;

import org.esteban.lescano.dmhouse.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email); // Búsqueda por Email
}


