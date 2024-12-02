package org.esteban.lescano.dmhouse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.esteban.lescano.dmhouse.DmHouseApplication;
import org.esteban.lescano.dmhouse.Exceptions.WalletNotFoundException;
import org.esteban.lescano.dmhouse.entities.Account;
import org.esteban.lescano.dmhouse.entities.Wallet;
import org.esteban.lescano.dmhouse.models.request.LoadBalanceRequest;
import org.esteban.lescano.dmhouse.models.response.BalanceResponse;
import org.esteban.lescano.dmhouse.models.response.TransactionResponse;
import org.esteban.lescano.dmhouse.services.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.esteban.lescano.dmhouse.models.response.BalanceResponse.*;

@Slf4j
@RestController
@RequestMapping("/api")
@Tag(name = "Wallet API", description = "API para operaciones relacionadas con Wallets") // Etiqueta para organizar el grupo de endpoints en Swagger
public class WalletController {

private final WalletService walletService;

public WalletController(WalletService walletService) {
	this.walletService = walletService;
}

	@Operation(
			summary = "Obtiene el balance de una wallet",
			description = "Retorna el balance de la wallet correspondiente al ID y la moneda especificada.",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Balance obtenido exitosamente",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = BalanceResponse.class))
					),
					@ApiResponse(responseCode = "404", description = "Wallet no encontrada"),
					@ApiResponse(responseCode = "400", description = "Solicitud inválida")
			}
	)
@GetMapping("/wallet/{id}/balance/{money}")
public ResponseEntity<BalanceResponse> getBalance(@Parameter(description = "ID de la wallet", example = "1") @PathVariable Integer id,
	                                                @Parameter(description = "Moneda para el balance", example = "USD") @PathVariable String money) {

	return ResponseEntity.ok(walletService.getBalance(id, money));
}

	@Operation(
			summary = "Carga saldo en una wallet",
			description = "Permite cargar saldo en la wallet especificada mediante una solicitud.",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Carga realizada exitosamente",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponse.class))
					),
					@ApiResponse(responseCode = "404", description = "Wallet no encontrada"),
					@ApiResponse(responseCode = "400", description = "Solicitud inválida")
			}
	)
	@PostMapping("/wallet/{id}/load")
	public ResponseEntity<TransactionResponse> loadBalance(
			@Parameter(description = "ID de la wallet", example = "1") @PathVariable Integer id,
			@RequestBody LoadBalanceRequest load) throws WalletNotFoundException {

		TransactionResponse response = new TransactionResponse();

		walletService.loadBalance(load.amount, load.currency, id, load.concept, "Detalle de envío de dinero");

		response.isOK = true;
		response.message = "Carga de saldo realizada exitosamente.";

		return ResponseEntity.ok(response);
	}

	@Operation(
			summary = "Obtiene el balance de una wallet",
			description = "Retorna el balance de la wallet correspondiente al ID y la moneda especificada.",
			responses = {
					@ApiResponse(
							responseCode = "200",
							description = "Balance obtenido exitosamente",
							content = @Content(mediaType = "application/json", schema = @Schema(implementation = BalanceResponse.class))
					),
					@ApiResponse(responseCode = "404", description = "Wallet no encontrada"),
					@ApiResponse(responseCode = "400", description = "Solicitud inválida")
			}
	)
	@GetMapping("/wallet/{id}/balance")
	public ResponseEntity<List<BalanceResponse>> getBalance(
			@Parameter(description = "ID de la wallet", example = "1")
			@PathVariable Integer id) throws WalletNotFoundException {

		List<BalanceResponse> balances = walletService.getWalletBalances(id);
		return ResponseEntity.ok(balances);
	}

}
