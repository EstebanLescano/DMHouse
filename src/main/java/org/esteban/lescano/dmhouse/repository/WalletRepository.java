package org.esteban.lescano.dmhouse.repository;

import org.esteban.lescano.dmhouse.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
	
	Wallet findByWalletId (Integer walletId);


	List<Wallet> findAllByUserId(Integer userId);
}
