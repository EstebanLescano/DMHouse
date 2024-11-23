package org.esteban.lescano.dmhouse.controller;

import org.esteban.lescano.dmhouse.models.request.LoadBalanceRequest;
import org.esteban.lescano.dmhouse.models.response.TransactionResponse;
import org.esteban.lescano.dmhouse.services.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

private final WalletService walletService;

public WalletController(WalletService walletService) {
	this.walletService = walletService;
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
