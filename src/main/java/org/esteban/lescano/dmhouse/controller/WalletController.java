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
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Slf4j
@RestController
@RequestMapping("/dmhouse")
@Tag(name = "Wallet API", description = "API para la gestión de wallets y balances.")
public class WalletController {

	private final WalletService walletService;

	public WalletController(WalletService walletService) {
		this.walletService = walletService;
	}

	@Operation(
			summary = "Obtiene el balance de una wallet",
			description = "Retorna el balance de la wallet correspondiente al ID y la moneda especificada.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Balance obtenido exitosamente"),
					@ApiResponse(responseCode = "404", description = "Wallet no encontrada"),
					@ApiResponse(responseCode = "400", description = "Solicitud inválida")
			}
	)
	@GetMapping("/wallet/{id}/balance")
	public ResponseEntity<?> getBalances(
			@Parameter(description = "ID de la wallet", example = "1") @PathVariable Integer id,
			@Parameter(description = "Moneda opcional para filtrar balances", example = "USD") @RequestParam(required = false) String currency
	) {
		if (currency != null) {
			return ResponseEntity.ok(walletService.getBalance(id, currency));
		}
		return ResponseEntity.ok(walletService.getWalletBalances(id));
	}

	@Operation(
			summary = "Carga saldo en una wallet",
			description = "Permite cargar saldo en la wallet especificada mediante una solicitud.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Carga realizada exitosamente"),
					@ApiResponse(responseCode = "404", description = "Wallet no encontrada"),
					@ApiResponse(responseCode = "400", description = "Solicitud inválida")
			}
	)
	@PostMapping("/wallet/{id}/load")
	public ResponseEntity<TransactionResponse> loadBalance(
			@Parameter(description = "ID de la wallet", example = "1") @PathVariable Integer id,
			@RequestBody LoadBalanceRequest load
	) {
		walletService.loadBalance(load.getAmount(), load.getCurrency(), id, load.getConcept(), "Detalle de envío de dinero");
		TransactionResponse response = new TransactionResponse(true, "Carga de saldo realizada exitosamente.");
		return ResponseEntity.ok(response);
	}

	@Operation(
			summary = "Obtiene todas las wallets de un usuario",
			description = "Retorna todas las wallets asociadas al usuario especificado por su ID.",
			responses = {
					@ApiResponse(responseCode = "200", description = "Wallets obtenidas exitosamente"),
					@ApiResponse(responseCode = "404", description = "Usuario no encontrado")
			}
	)
	@GetMapping("/wallet/{id}")
	public ResponseEntity<List<Wallet>> getWallets(@PathVariable Integer id) {
		return ResponseEntity.ok(walletService.getWallets(id));
	}
}
