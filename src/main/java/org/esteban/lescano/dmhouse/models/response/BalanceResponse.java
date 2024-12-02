package org.esteban.lescano.dmhouse.models.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Getter
@Setter
@Service
public class BalanceResponse {
	public static String money;
	public static BigDecimal balance;

	public BalanceResponse() {
	}

	public BalanceResponse(BigDecimal balance, String money) {
		BalanceResponse.balance = balance;
		BalanceResponse.money = money;
	}
}
