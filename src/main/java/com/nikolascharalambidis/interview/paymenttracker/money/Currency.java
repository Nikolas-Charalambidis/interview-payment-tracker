package com.nikolascharalambidis.interview.paymenttracker.money;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A class representing a currency with a rate to the USD
 */
public enum Currency {

	USD("1"),
	HKG("0.127539"),
	GBP("1.28193"),
	RMB("0.143299"),
	NDZ("0.636576");

	/**
	 * An exchange rate to the USD
	 */
	private final BigDecimal exchangeRate;

	Currency(final String exchangeRate) {
		this.exchangeRate = new BigDecimal(exchangeRate);
	}

	public final BigDecimal exchangeRate() {
		return exchangeRate.setScale(16, RoundingMode.HALF_EVEN).stripTrailingZeros();
	}
}
