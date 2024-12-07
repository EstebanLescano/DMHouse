package org.esteban.lescano.dmhouse.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.esteban.lescano.dmhouse.enums.StatusTransactionEnum;
import org.esteban.lescano.dmhouse.enums.TransactionConceptEnum;
import org.esteban.lescano.dmhouse.enums.TransactionTypeEnum;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Integer transactionId;

	@ManyToOne
	@JoinColumn(name = "from_account_id", referencedColumnName = "account_id", nullable = false)
	private Account fromAccount;

	@ManyToOne
	@JoinColumn(name = "to_account_id", referencedColumnName = "account_id")
	private Account toAccount;

	@Column(name = "transaction_date", nullable = false)
	private Date transactionDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_type", nullable = false)
	private TransactionTypeEnum transactionType;

	@Enumerated(EnumType.STRING)
	@Column(name = "transaction_concept", nullable = false)
	private TransactionConceptEnum transactionConcept;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private StatusTransactionEnum status;

	@Column(name = "amount", nullable = false)
	@Positive
	private BigDecimal amount;

	@Column(name = "currency", nullable = false)
	private String currency;

	@ManyToOne
	@JoinColumn(name = "from_user_id", referencedColumnName = "client_id")
	private Client fromUser;

	@ManyToOne
	@JoinColumn(name = "to_user_id", referencedColumnName = "client_id")
	private Client toUser;

	@Column(name = "details")
	private String details;

	// Constructor útil
	public Transaction(BigDecimal amount, TransactionConceptEnum concept, String details, StatusTransactionEnum status) {
		this.amount = amount;
		this.transactionConcept = concept;
		this.details = details;
		this.status = status;
		this.transactionDate = new Date(); // Fecha actual por defecto
	}
}