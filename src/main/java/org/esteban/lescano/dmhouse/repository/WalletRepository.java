package org.esteban.lescano.dmhouse.repository;

import org.esteban.lescano.dmhouse.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
	
	Wallet findByWalletId (Integer wallet_id);


 
}
