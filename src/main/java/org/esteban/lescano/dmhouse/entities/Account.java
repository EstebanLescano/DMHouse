package org.esteban.lescano.dmhouse.entities;

import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "account")
public class Account {

@Id
@Column(name = "account_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer accountId;

	@Column(name = "balance", nullable = false)
private BigDecimal balance;

	@Column(name = "money", nullable = false)
private String money;

@Setter
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "wallet_id", referencedColumnName = "walletId")
private Wallet wallet;

@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
		if (transaction != null) {
			transaction.setAccount(this);
			this.transactions.add(transaction);
		}
	}
}