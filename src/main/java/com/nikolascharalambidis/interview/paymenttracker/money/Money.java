package com.nikolascharalambidis.interview.paymenttracker.money;

import java.math.BigDecimal;

/**
 * An interface representing money
 */
public interface Money {

	/**
	 * New money instance updated with an amount
	 * @param amount An amount to be updated
	 * @return New money with the same currency
	 */
	Money with(final BigDecimal amount);

	/**
	 * New money instance created as a conversion to a different currency
	 * @param currency A currency to be converted to
	 * @return New money with a different currency
	 */
	Money convertedTo(final Currency currency);

	/**
	 * Returns the monetary amount
	 * @return The amount
	 */
	BigDecimal amount();

	/**
	 * Returns the currency
	 * @return The currency
	 */
	Currency currency();

	/**
	 * Prints the money to the console
	 * @param currency Currency to be also displayed aside of the bound currency
	 */
	void print(final Currency... currency);
}
