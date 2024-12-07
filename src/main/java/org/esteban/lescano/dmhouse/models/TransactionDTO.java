package org.esteban.lescano.dmhouse.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Integer transactionId;
    private Date dateTransaction;
    private Integer stateId;
    private BigDecimal amount;
    private String currency;
    private String transactionType; // Enum as String
    private String transactionConcept; // Enum as String
    private String details;
    private Integer fromUserId;
    private Integer toUserId;
    private Integer fromAccountId;
    private Integer toAccountId;
}

