package org.esteban.lescano.dmhouse.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "wallet")
public class Wallet {

@Id
@Column(name = "wallet_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer wallet_id;

@OneToOne
@JoinColumn(name = "person_id",referencedColumnName = "person_id")
private Person person;

@OneToMany(mappedBy = "wallet_id",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
private List<Account> account = new ArrayList<>();

public void addAccount(Account account) {
	this.account.add(account);
	account.setWallet(this);
}

public Account getAccount (String money) {
	for (Account account : this.account) {
		if (account.getMoney().equals(money)) {
			return account;
		}
	}
	return null;
}
}