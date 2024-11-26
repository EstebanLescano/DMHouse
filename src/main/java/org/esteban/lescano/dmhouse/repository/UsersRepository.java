package org.esteban.lescano.dmhouse.repository;

import org.esteban.lescano.dmhouse.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	Users findByUserName (String userName);
	Users findByEmail (String email);
}