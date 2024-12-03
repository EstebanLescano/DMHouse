package org.esteban.lescano.dmhouse.models.response;

import org.esteban.lescano.dmhouse.entities.Transaction;

import java.math.BigDecimal;
import java.util.Date;

public class MovmentsResponse {

    public Integer numberTransaccion;
    public Date dateOfTransaction;
    public BigDecimal valueTrans;
    public String moneda;
    public Transaction.TransactionTypeEnum transactionTypeEnum;
    public Transaction.TransactionConceptEnum transactionConceptEnum;
    public String detail;
    public String aUser;

}