package org.esteban.lescano.dmhouse.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

@Id
@Column(name = "transaction_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer transactionId;
@ManyToOne
@JoinColumn(name = "acount_id", referencedColumnName = "acount_id")
private Acount acount;
private Date DateTransaction;
@Column(name = "estado_id")
private Integer stateId;
private BigDecimal amount;
private String currency;
@Column(name = "transaction_type")
private TransactionTypeEnum transactionType;
@Column(name = "transaction_concept")
private TransactionConceptEnum transactionConcept;
private String details;
@Column(name = "from_user_id")
private Integer fromUserId;
@Column(name = "to_user_id")
private Integer toUserId;
@Column(name = "from_account_id")
private Integer fromAccountId;
@Column(name = "to_account_id")
private Integer toAccountId;

/***
 * En este caso es un ENUMERADO con numeracion default En JAVA. Estos comienzan
 * desde 0 y si intercambiamos el orden el 0 pasa a ser siempre el primero. Si
 * quisieramos tener uno customizado, en JAVA es mas complejo(se ahoga en un
 * vaso de agua)
 * estos enum debria hacerlos en otra clase que sea exclusiva
 */
public enum TransactionTypeEnum {
	outgoing, // Este es siempre 0
	incoming // Este es siempre 1
}

public enum TransactionConceptEnum {
	RECHARGE, // Este es siempre 0
	SEND // Este es siempre 1
}
}
