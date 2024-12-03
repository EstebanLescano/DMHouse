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
		// Validar que el ID no sea nulo
		if (walletId == null) {
			throw new IllegalArgumentException("Wallet ID cannot be null.");
		}
		Wallet wallet = walletRepository.findByWalletId(walletId);

		// Validar si la wallet existe
		if (wallet == null) {
			throw new WalletNotFoundException("Wallet with ID " + walletId + " not found.");
		}

		// Validar que la wallet tenga cuentas asociadas
		if (wallet.getAccounts() == null || wallet.getAccounts().isEmpty()) {
			throw new IllegalStateException("Wallet with ID " + walletId + " has no accounts.");
		}

		// Filtra las cuentas con saldo válido y retorna los balances
		return wallet.getAccounts().stream()
				.filter(account -> account.getBalance() != null)
				.map(account -> new BalanceResponse(account.getBalance(), account.getMoney()))
				.toList();
	}

	// Obtener el saldo específico de una moneda
	public BigDecimal balanceRequest(Integer walletId, String money) throws WalletNotFoundException {
		// Validar parámetros de entrada
		if (walletId == null || money == null || money.isBlank()) {
			throw new IllegalArgumentException("Wallet ID and money cannot be null or empty.");
		}

		Wallet wallet = walletRepository.findByWalletId(walletId);

		// Validar si la wallet existe
		if (wallet == null) {
			throw new WalletNotFoundException("Wallet with ID " + walletId + " not found.");
		}

		// Filtra la cuenta por moneda y devuelve el balance, o BigDecimal.ZERO si no encuentra
		return wallet.getAccounts().stream()
				.filter(account -> account.getMoney().equalsIgnoreCase(money))
				.map(Account::getBalance)
				.findFirst()
				.orElse(BigDecimal.ZERO);
	}

	// Cargar saldo en una wallet
	public void loadBalance(BigDecimal balance, String money, Integer walletId,
	                        Transaction.TransactionConceptEnum concept, String details)
			throws WalletNotFoundException {
		// Validar parámetros de entrada
		if (balance == null || balance.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Balance must be greater than zero.");
		}
		if (money == null || money.isBlank()) {
			throw new IllegalArgumentException("Money cannot be null or empty.");
		}
		if (walletId == null) {
			throw new IllegalArgumentException("Wallet ID cannot be null.");
		}

		Wallet wallet = walletRepository.findByWalletId(walletId);

		// Validar si la wallet existe
		if (wallet == null) {
			throw new WalletNotFoundException("Wallet with ID " + walletId + " not found.");
		}

		// Busca la cuenta asociada a la moneda
		Account account = wallet.getAccounts().stream()
				.filter(acc -> acc.getMoney().equalsIgnoreCase(money))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Account with money " + money + " not found."));

		// Actualiza el saldo de la cuenta
		account.setBalance(account.getBalance().add(balance));

		// Opcional: registra la transacción o guarda detalles adicionales
		Transaction transaction = new Transaction(balance, concept, details);
		account.addTransaction(transaction);

		// Guarda los cambios en el repositorio
		walletRepository.save(wallet);
	}

	// Obtener un BalanceResponse específico por ID y moneda
	public BalanceResponse getBalance(Integer walletId, String money) throws WalletNotFoundException {
		// Reutilizamos el méttodo balanceRequest para obtener el saldo
		BigDecimal balance = balanceRequest(walletId, money);
		return new BalanceResponse(balance, money);
	}

	// Obtener todas las wallets asociadas a un ID de usuario
	public List<Wallet> getWallets(Integer userId) {
		// Validar que el ID de usuario no sea nulo
		if (userId == null) {
			throw new IllegalArgumentException("User ID cannot be null.");
		}

		List<Wallet> wallets = walletRepository.findAllByUserId(userId);

		// Validar si el usuario tiene wallets asociadas
		if (wallets == null || wallets.isEmpty()) {
			throw new IllegalStateException("User with ID " + userId + " has no wallets.");
		}

		return wallets;
	}
}

