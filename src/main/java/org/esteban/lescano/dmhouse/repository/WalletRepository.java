package org.esteban.lescano.dmhouse.repository;

import org.esteban.lescano.dmhouse.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    Optional<Wallet> findById(Integer id);

}
