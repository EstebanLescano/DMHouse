package org.esteban.lescano.dmhouse.entities;

import jakarta.persistence.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "acount")
public class Acount {

@Id
@Column(name = "acount_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer acountId;
private BigDecimal balance;
private String money;
@ManyToOne
@JoinColumn(name = "wallet_id", referencedColumnName = "wallet_id")
private Wallet wallet;
@OneToMany(mappedBy = "acount", cascade = CascadeType.ALL)
@LazyCollection(LazyCollectionOption.FALSE)
private List<Transaction> transactions = new ArrayList<>();

}