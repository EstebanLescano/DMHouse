package org.esteban.lescano.dmhouse.services;

import org.esteban.lescano.dmhouse.Exceptions.WalletNotFoundException;
import org.esteban.lescano.dmhouse.entities.Account;
import org.esteban.lescano.dmhouse.entities.Transaction;
import org.esteban.lescano.dmhouse.entities.Wallet;
import org.esteban.lescano.dmhouse.models.response.BalanceResponse;
import org.esteban.lescano.dmhouse.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WalletService {

	private final WalletRepository walletRepository;

	public WalletService(WalletRepository walletRepository) {
		this.walletRepository = walletRepository;
	}

	public void save(Wallet wallet) {
		walletRepository.save(wallet);
	}

	public Wallet findWalletForId(Integer id) {
		return walletRepository.findByWalletId(id);
	}

	public List<BalanceResponse> getWalletBalances(Integer walletId) throws WalletNotFoundException {
		Wallet wallet = this.findWalletForId(walletId);
		if (wallet == null) {
			throw new WalletNotFoundException("Wallet with ID " + walletId + " not found.");
		}

		return wallet.getAccounts().stream()
				.filter(account -> account.getBalance() != null)
				.map(account -> new BalanceResponse(account.getBalance(), account.getMoney()))
				.toList();
	}

	public BigDecimal balanceRequest(Integer walletId, String money) {
		return walletRepository.findByWalletId(walletId)
				.getAccount()
				.stream()
				.filter(account -> account.getMoney().equals(money))
				.map(Account::getBalance)
				.findFirst()
				.orElse(BigDecimal.ZERO);
	}

	public void loadBalance(BigDecimal balance, String money, Integer walletId, Transaction.TransactionConceptEnum concept, String details) throws WalletNotFoundException {
		Wallet wallet = this.findWalletForId(walletId);
		if (wallet == null) {
			throw new WalletNotFoundException("Wallet with ID " + walletId + " not found.");
		}
		// Lógica adicional para cargar saldo
	}

	public BalanceResponse getBalance(Integer id, String money) {
		return null;
	}
}