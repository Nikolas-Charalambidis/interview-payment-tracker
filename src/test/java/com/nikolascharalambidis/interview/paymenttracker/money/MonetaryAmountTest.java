package com.nikolascharalambidis.interview.paymenttracker.money;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MonetaryAmountTest {

	@Test
	void testWithReturnsHigherMonetaryAmount() {
		Assertions.assertEquals(
			new MonetaryAmount(BigDecimal.valueOf(11L), Currency.GBP),
			new MonetaryAmount(BigDecimal.TEN, Currency.GBP)
				.with(BigDecimal.ONE));
	}

	@Test
	void testWithReturnsLowerMonetaryAmount() {
		Assertions.assertEquals(
			new MonetaryAmount(BigDecimal.valueOf(9L), Currency.GBP),
			new MonetaryAmount(BigDecimal.TEN, Currency.GBP)
				.with(BigDecimal.ONE.negate()));
	}

	@Test
	void testConversionToDifferentCurrency() {
		Assertions.assertEquals(
			new MonetaryAmount(new BigDecimal("12.8193"), Currency.USD),
			new MonetaryAmount(BigDecimal.TEN, Currency.GBP)
				.convertedTo(Currency.USD));
	}

	@Test
	void testConversionToSameCurrency() {
		Assertions.assertEquals(
			new MonetaryAmount(BigDecimal.TEN, Currency.GBP),
			new MonetaryAmount(BigDecimal.TEN, Currency.GBP)
				.convertedTo(Currency.GBP));
	}
}
