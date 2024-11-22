package org.esteban.lescano.dmhouse.Services;

import org.esteban.lescano.dmhouse.Repository.WalletRepository;
import org.esteban.lescano.dmhouse.entities.Wallet;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

private final WalletRepository walletRepository;

public WalletService(WalletRepository walletRepository) {
	this.walletRepository = walletRepository;
}

public void saveWallet(Wallet wallet) {
	walletRepository.save(wallet);
}

}
