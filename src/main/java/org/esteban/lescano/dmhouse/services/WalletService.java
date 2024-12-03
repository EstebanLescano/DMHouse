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

	// Obtener los saldos de las cuentas de una wallet
	public List<BalanceResponse> getWalletBalances(Integer walletId) throws WalletNotFoundException {
		Wallet wallet = walletRepository.findByWalletId(walletId);

		if (wallet == null) {
			throw new WalletNotFoundException("Wallet with ID " + walletId + " not found.");
		}

		// Filtra las cuentas que tienen saldo y mapea a una lista de BalanceResponse
		return wallet.getAccounts().stream()
				.filter(account -> account.getBalance() != null)
				.map(account -> new BalanceResponse(account.getBalance(), account.getMoney()))
				.toList();
	}

	// Obtener el saldo específico de una moneda
	public BigDecimal balanceRequest(Integer walletId, String money) throws WalletNotFoundException {
		Wallet wallet = walletRepository.findByWalletId(walletId);

		if (wallet == null) {
			throw new WalletNotFoundException("Wallet with ID " + walletId + " not found.");
		}

		// Filtra la cuenta por moneda y devuelve el balance, o BigDecimal.ZERO si no encuentra
		return wallet.getAccounts().stream()
				.filter(account -> account.getMoney().equals(money))
				.map(Account::getBalance)
				.findFirst()
				.orElse(BigDecimal.ZERO);
	}

	// Cargar saldo en una wallet
	public void loadBalance(BigDecimal balance, String money, Integer walletId,
	                        Transaction.TransactionConceptEnum concept, String details)
			throws WalletNotFoundException {
		Wallet wallet = walletRepository.findByWalletId(walletId);

		if (wallet == null) {
			throw new WalletNotFoundException("Wallet with ID " + walletId + " not found.");
		}

		// Busca la cuenta asociada a la moneda
		Account account = wallet.getAccounts().stream()
				.filter(acc -> acc.getMoney().equals(money))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Account with money " + money + " not found."));

		// Actualiza el saldo
		account.setBalance(account.getBalance().add(balance));

		// Registra la transacción o guarda detalles adicionales
		Transaction transaction = new Transaction(balance, concept, details);
		account.addTransaction(transaction);
		walletRepository.save(wallet); //guarda los cambios
	}

	// Obtener un BalanceResponse específico por id y moneda
	public BalanceResponse getBalance(Integer walletId, String money) throws WalletNotFoundException {
		BigDecimal balance = balanceRequest(walletId, money);
		return new BalanceResponse(balance, money);
	}

	// Obtener todas las wallets asociadas a un ID de usuario
	public List<Wallet> getWallets(Integer userId) {
		return walletRepository.findAllByUserId(userId);
	}

}
