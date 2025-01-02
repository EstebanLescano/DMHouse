package org.esteban.lescano.dmhouse.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.esteban.lescano.dmhouse.entities.Wallet;
import org.esteban.lescano.dmhouse.enums.TransactionConceptEnum;
import org.esteban.lescano.dmhouse.services.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/DMHouse")
@Tag(name = "Wallet", description = "Manage wallet-related operations")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @Operation(
            summary = "Carga saldo en una wallet",
            description = "Carga una cantidad específica de saldo en una wallet, especificando la moneda y el concepto de la transacción.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Saldo cargado exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Wallet no encontrada"),
                    @ApiResponse(responseCode = "400", description = "Parámetros inválidos")
            }
    )
    @PostMapping("/wallet/{id}/balance")
    public ResponseEntity<?> loadBalance(
            @Parameter(description = "ID de la wallet", example = "1") @PathVariable Integer id,
            @Parameter(description = "Cantidad de saldo a cargar", example = "100.0") @RequestParam BigDecimal amount,
            @Parameter(description = "Moneda de la carga de saldo", example = "USD") @RequestParam String currency,
            @Parameter(description = "Concepto de la transacción", example = "DEPOSIT") @RequestParam TransactionConceptEnum concept,
            @Parameter(description = "Detalles adicionales de la transacción") @RequestParam(required = false) String details
    ) {
        walletService.loadBalance(amount, currency, id, concept, details);
        return ResponseEntity.ok().body(Map.of("message", "Balance loaded successfully"));
    }

    @Operation(
            summary = "Obtiene todas las wallets de un usuario",
            description = "Retorna todas las wallets asociadas al usuario especificado por su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Wallets obtenidas exitosamente"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
            }
    )
    @GetMapping("/wallets/{userId}")
    public ResponseEntity<?> getWallets(
            @Parameter(description = "ID del usuario", example = "1") @PathVariable Integer userId
    ) {
        Optional<Wallet> wallets = walletService.getWalletsByClient(userId);
        if (wallets.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of());
        }
        return ResponseEntity.ok(wallets);
    }
}
