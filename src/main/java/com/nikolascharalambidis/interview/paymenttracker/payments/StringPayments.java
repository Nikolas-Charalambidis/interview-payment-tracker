package com.nikolascharalambidis.interview.paymenttracker.payments;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nikolascharalambidis.interview.paymenttracker.money.Currency;
import com.nikolascharalambidis.interview.paymenttracker.money.MonetaryAmount;
import com.nikolascharalambidis.interview.paymenttracker.money.Money;

public class StringPayments implements Payments {

	private static final Pattern PATTERN = Pattern.compile("[A-Za-z]{3}\\s+-?\\d+([.,]\\d+)?");

	private final List<String> lines;

	public StringPayments(final String... lines) {
		this.lines = Arrays.asList(lines);
	}

	@Override
	public List<Money> payments() {
		final List<Money> moneyList = new ArrayList<>();
		for (int i = 0; i < this.lines.size(); i++) {
			final String line = this.lines.get(i);
			final Matcher matcher = PATTERN.matcher(line);
			if (matcher.matches()) {
				final String[] split = line.split("\\s+");
				try {
					final Currency currency = Currency.valueOf(split[0].toUpperCase());
					final BigDecimal amount = new BigDecimal(split[1].replaceAll(",", "."));
					moneyList.add(new MonetaryAmount(amount, currency));
				} catch (IllegalArgumentException ex) {
					System.err.println(String.format("Invalid currency at line %s. Try again, example: 'USD 1000'", i+1));
				}
			} else {
				if (!line.trim().isEmpty()) {
					System.err.println(String.format("Malformed input at line %s. Try again, example: 'USD 1000'", i+1));
				}
			}
		}
		return moneyList;
	}
}
