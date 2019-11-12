package com.nikolascharalambidis.interview.paymenttracker.money;

import java.math.BigDecimal;

public interface Money {

	Money with(final BigDecimal amount);

	Money convertedTo(final Currency currency);

	BigDecimal amount();

	Currency currency();

	void print(final Currency... currency);
}
