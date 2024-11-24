package org.esteban.lescano.dmhouse.controller;

import org.esteban.lescano.dmhouse.models.request.LoadBalanceRequest;
import org.esteban.lescano.dmhouse.models.response.BalanceResponse;
import org.esteban.lescano.dmhouse.models.response.TransactionResponse;
import org.esteban.lescano.dmhouse.services.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class WalletController {

private final WalletService walletService;

public WalletController(WalletService walletService) {
	this.walletService = walletService;
}

@GetMapping("/wallet/{id}/balance/{money}")
public ResponseEntity<BalanceResponse> getBalance(@PathVariable Integer id, @PathVariable String money){
	BalanceResponse balanceR= new BalanceResponse();
	balanceR.balance = walletService.BalanceRequest(id, money);
	balanceR.money = money;
	return ResponseEntity.ok(balanceR);
}

@PostMapping("/wallet/{id}/load")
public ResponseEntity<TransactionResponse> loadBalance(@PathVariable Integer id, @RequestBody LoadBalanceRequest load){

	TransactionResponse response = new TransactionResponse();
	
	walletService.loadBalance(load.amount, load.currency, id, load.concept, "detralle de envio de dinero");

	response.isOK = true;
	response.message = "Load Balance is Successfull!!!";
	
	return ResponseEntity.ok(response);
}
}
