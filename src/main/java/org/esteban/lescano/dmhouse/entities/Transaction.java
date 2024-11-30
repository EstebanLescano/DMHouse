package org.esteban.lescano.dmhouse.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {

@Id
@Column(name = "transaction_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer transactionId;

@ManyToOne
@JoinColumn(name = "wallet_id", referencedColumnName = "wallet_id")
private Account account;

private Date DateTransaction;

@Column(name = "state_id")
private Integer stateId;

private BigDecimal amount;

private String currency;

@Enumerated(EnumType.STRING)
@Column(name = "transaction_type")
private TransactionTypeEnum transactionType;

@Enumerated(EnumType.STRING)
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
	LOAD, // Este es siempre 0
	SEND // Este es siempre 1
}
public enum ResultadoTransaccionEnum {
	NEGATIVE_IMPORT_ERROR,
	INITIATED,
	INSUFFICIENT_BALANCE,
	DESTINATION_WALLET_NOT_FOUND,
	SOURCE_WALLET_NOT_FOUND,
	DAILY_LIMIT_REACHED,
	NONEXISTENT_ORIGIN_ACCOUNT,
	NONEXISTENT_DESTINATION_ACCOUNT,
	WILL_WANT_TO_PAY_ITSELF,
	NONEXISTENT_DESTINATION_EMAIL
}

/***
 * En este caso es un ENUMERADO con numeracion customizada En JAVA, los
 * enumerados con numeros customizados deben tener un constructor y un
 * comparador para poder funcionar correctamente
 */
// Este es un ejemplo de enumerado de estados customizados.
public enum StatusTransactionEnum {
	PENDING(0),
	SEND(1),
	RECEIVED(2),
	EXECUTED(4),
	MISSING_FUNDS(80),
	GENERAL_ERROR(99);
	
	private final int value;
	// NOTE: Enum constructor tiene que estar en privado
	private StatusTransactionEnum(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static StatusTransactionEnum parse(int id) {
		StatusTransactionEnum status = null; // Default
		for (StatusTransactionEnum item : StatusTransactionEnum.values()) {
			if (item.getValue() == id) {
				status = item;
				break;
			}
		}
		return status;
	}
}

}
