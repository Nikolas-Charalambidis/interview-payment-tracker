package com.nikolascharalambidis.interview.paymenttracker.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A pair of decimal number bound to a currency. Together representing money.
 */
public class MonetaryAmount implements Money {

	/**
	 * An amount of the money in the specified currency
	 */
	private BigDecimal amount;

	/**
	 * A currency
	 */
	private Currency currency;

	/**
	 * A primary constructor
	 * @param amount An amount of the money in the specified currency
	 * @param currency A currency
	 */
	public MonetaryAmount(final BigDecimal amount, final Currency currency) {
		this.amount = amount.setScale(16, RoundingMode.HALF_EVEN);
		this.currency = currency;
	}

	@Override
	public Money with(final BigDecimal amount) {
		return new MonetaryAmount(this.amount.add(amount), this.currency);
	}

	@Override
	public Money convertedTo(final Currency c) {
		return new MonetaryAmount(this.amount
			.divide(c.exchangeRate(), RoundingMode.HALF_EVEN)
			.multiply(this.currency.exchangeRate()), c);
	}

	@Override
	public BigDecimal amount() {
		return this.amount;
	}

	@Override
	public Currency currency() {
		return this.currency;
	}

	@Override
	public String toString() {
		return String.format("%s %s", this.currency.name(), this.amount.stripTrailingZeros().toPlainString());
	}

	@Override
	public void print(final Currency... currencies) {
		final String suffix = Arrays.stream(currencies)
			.filter(c -> c != this.currency)
			.map(this::convertedTo)
			.map(Money::toString)
			.filter(s -> !s.trim().isEmpty())
			.collect(Collectors.collectingAndThen(
				Collectors.joining(", "),
				s -> s.trim().isEmpty() ? s : " (" + s + ")"));
		final String output = String.format("%s%s", this.toString(), suffix);
		System.out.println(output);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof MonetaryAmount)) {
			return false;
		}
		final MonetaryAmount that = (MonetaryAmount) o;
		return amount.equals(that.amount) &&
			currency == that.currency;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, currency);
	}
}
