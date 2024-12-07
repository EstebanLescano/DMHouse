package org.esteban.lescano.dmhouse.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BalanceDTO {
    private BigDecimal balance;
    private String currency;

    public BalanceDTO(BigDecimal balance, String currency) {
        this.balance = balance;
        this.currency = currency;
    }
}