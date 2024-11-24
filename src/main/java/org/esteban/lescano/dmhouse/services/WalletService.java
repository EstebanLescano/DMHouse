package org.esteban.lescano.dmhouse.services;

import org.esteban.lescano.dmhouse.entities.Account;
import org.esteban.lescano.dmhouse.entities.Transaction;
import org.esteban.lescano.dmhouse.entities.Wallet;
import org.esteban.lescano.dmhouse.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
public class WalletService {

private final WalletRepository walletRepository;

public void save(Wallet wallet) {
	walletRepository.save(wallet);
}

public WalletService(WalletRepository walletRepository) {
	this.walletRepository = walletRepository;
}

public void loadBalance(BigDecimal balance, String money, Integer walletId, Transaction.TransactionConceptEnum concept, String details){
	Wallet wallet = this.findWalletForId(walletId);
	loadBalance(balance, money, wallet.getWallet_id(), concept, details);
}

public Wallet findWalletForId(Integer id) {
	return walletRepository.findByWalletId(id);
}

public BigDecimal BalanceRequest(Integer walletId, String money){
	return walletRepository.findByWalletId(walletId)
			       .getAccount()
			       .stream()
			       .filter(cuenta -> cuenta.getAcountId().equals(money))
			       .map(Account::getBalance)
			       .findFirst()
			       .orElse(BigDecimal.ZERO);
}

}
