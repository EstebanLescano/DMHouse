package org.esteban.lescano.dmhouse.models.request;

import org.esteban.lescano.dmhouse.entities.Transaction;

import java.math.BigDecimal;

public class LoadBalanceRequest {

public BigDecimal amount;
public String currency;
public Transaction.TransactionConceptEnum concept;
}
