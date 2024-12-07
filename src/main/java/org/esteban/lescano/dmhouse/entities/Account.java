package org.esteban.lescano.dmhouse.entities;

import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
@Table(name = "account")
public class Account {

@Id
@Column(name = "account_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

	@Column(nullable = false)
	private String currency; // Ejemplo: "USD", "ARS"

	@Column(nullable = false)
	private BigDecimal balance;

	@ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}