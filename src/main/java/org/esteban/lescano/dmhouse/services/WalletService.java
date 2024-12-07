package org.esteban.lescano.dmhouse.services;

import org.esteban.lescano.dmhouse.entities.Client;
import org.esteban.lescano.dmhouse.entities.Wallet;
import org.esteban.lescano.dmhouse.enums.TransactionConceptEnum;
import org.esteban.lescano.dmhouse.repository.AccountRepository;
import org.esteban.lescano.dmhouse.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WalletService {

	private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository, AccountRepository accountRepository) {
		this.walletRepository = walletRepository;
    }

	// Obtener todas las wallets de un cliente específico
	public Optional<Wallet> getWalletsByClient(Integer clientId) {
		if (clientId == null) {
			throw new IllegalArgumentException("Client ID cannot be null.");
		}

		return walletRepository.findById(2);
	}

	public Wallet createWallet(Client client) {
		Wallet wallet = new Wallet();
		wallet.setClient(client);
		return walletRepository.save(wallet);
	}

	public void loadBalance(BigDecimal amount, String currency, Integer id, TransactionConceptEnum concept, String details) {

	}
}
