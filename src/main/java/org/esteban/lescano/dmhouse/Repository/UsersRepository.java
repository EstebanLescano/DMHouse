package org.esteban.lescano.dmhouse.Repository;

import org.esteban.lescano.dmhouse.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}