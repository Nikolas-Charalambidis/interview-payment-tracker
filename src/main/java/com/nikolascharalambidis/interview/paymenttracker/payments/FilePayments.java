package com.nikolascharalambidis.interview.paymenttracker.payments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.nikolascharalambidis.interview.paymenttracker.money.Money;
import com.nikolascharalambidis.interview.paymenttracker.util.Lines;

/**
 * A representation of payments loaded from a file
 */
public class FilePayments implements Payments {

	/**
	 * Source file name
	 */
	private final String fileName;

	/**
	 * A primary constructor
	 * @param fileName A file name
	 */
	public FilePayments(final String fileName) {
		this.fileName = Optional.of(fileName)
			.filter(s -> s.startsWith(">"))
			.map(s -> s.substring(1))
			.orElse(fileName).trim();
	}

	@Override
	public final List<Money> payments() {
		final List<Money> moneyList = new ArrayList<>();
		try {
			final List<String> lines = new Lines(this.fileName).get();
			System.out.println(String.format("File %s loaded successfully.", this.fileName));
			final String[] arrayOfLines = lines.toArray(new String[0]);
			moneyList.addAll(new StringPayments(arrayOfLines).payments());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return moneyList;
	}
}
