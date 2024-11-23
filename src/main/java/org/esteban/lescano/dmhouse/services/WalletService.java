package org.esteban.lescano.dmhouse.services;

import org.esteban.lescano.dmhouse.entities.Transaction;
import org.esteban.lescano.dmhouse.entities.Wallet;
import org.esteban.lescano.dmhouse.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {

private final WalletRepository walletRepository;

public void save(Wallet wallet) {
	walletRepository.save(wallet);
}

public WalletService(WalletRepository walletRepository) {
	this.walletRepository = walletRepository;
}

public void loadBalance(BigDecimal balance, String money, Integer wallet, Transaction.TransactionConceptEnum concept, String details){

}

}
