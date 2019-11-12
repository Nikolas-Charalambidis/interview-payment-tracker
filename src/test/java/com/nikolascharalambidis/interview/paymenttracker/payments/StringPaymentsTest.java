package com.nikolascharalambidis.interview.paymenttracker.payments;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.nikolascharalambidis.interview.paymenttracker.money.Currency;
import com.nikolascharalambidis.interview.paymenttracker.money.MonetaryAmount;

class StringPaymentsTest {

	@Test
	void testValidInputs() {
		Assertions.assertEquals(
			Arrays.asList(
				new MonetaryAmount(BigDecimal.valueOf(100L), Currency.USD),
				new MonetaryAmount(BigDecimal.valueOf(100L), Currency.GBP),
				new MonetaryAmount(BigDecimal.valueOf(200L), Currency.GBP)),
			new StringPayments("USD 100", "GBP 100", "GBP 200").payments()
		);
	}

	@Test
	void testValidDecimalInputs() {
		Assertions.assertEquals(
			Arrays.asList(
				new MonetaryAmount(BigDecimal.valueOf(100.5D), Currency.USD),
				new MonetaryAmount(BigDecimal.valueOf(100.25D), Currency.GBP),
				new MonetaryAmount(BigDecimal.valueOf(200.225D), Currency.GBP)),
			new StringPayments("USD 100.5", "GBP 100,25", "GBP 200.225").payments()
		);
	}

	@Test
	void testValidInputsWithWhiteCharacters() {
		Assertions.assertEquals(
			Arrays.asList(
				new MonetaryAmount(BigDecimal.valueOf(100L), Currency.USD),
				new MonetaryAmount(BigDecimal.valueOf(100L), Currency.GBP),
				new MonetaryAmount(BigDecimal.valueOf(200L), Currency.GBP)),
			new StringPayments("USD		100", "GBP   100", "GBP	 	 200").payments()
		);
	}

	@Test
	void testInvalidInputs() {
		Assertions.assertEquals(
			Arrays.asList(
				new MonetaryAmount(BigDecimal.valueOf(100L), Currency.USD),
				new MonetaryAmount(BigDecimal.valueOf(200L), Currency.GBP)),
			new StringPayments("USD 100", "malformed", "GBP 200", "USD 200 150").payments()
		);
	}

	@Test
	void testInvalidDecimalInputs() {
		Assertions.assertEquals(
			Collections.singletonList(new MonetaryAmount(BigDecimal.valueOf(100L), Currency.USD)),
			new StringPayments("USD 100.5.20", "GBP 100.25,10", "USD 100", "GBP .225").payments()
		);
	}
}
