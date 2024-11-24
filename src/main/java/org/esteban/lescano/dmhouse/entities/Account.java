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
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "account")
public class Account {

@Id
@Column(name = "account_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer acountId;

private BigDecimal balance;

private String money;

@ManyToOne
@JoinColumn(name = "wallet_id", referencedColumnName = "wallet_id")
private Wallet wallet;

@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
private List<Transaction> transactions = new ArrayList<>();

public void setWallet (Wallet wallet){
	this.wallet = wallet;
}

}