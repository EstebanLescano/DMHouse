package org.esteban.lescano.dmhouse.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wallet")
public class Wallet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wallet_id", unique = true)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false)
	private Client client;

	@OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Account> accounts = new ArrayList<>();

	public Wallet() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer walletId) {
		this.id = walletId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
