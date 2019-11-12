package com.nikolascharalambidis.interview.paymenttracker;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.nikolascharalambidis.interview.paymenttracker.money.Currency;
import com.nikolascharalambidis.interview.paymenttracker.money.Money;
import com.nikolascharalambidis.interview.paymenttracker.payments.FilePayments;
import com.nikolascharalambidis.interview.paymenttracker.payments.Payments;
import com.nikolascharalambidis.interview.paymenttracker.payments.StringPayments;
import com.nikolascharalambidis.interview.paymenttracker.util.ConsoleNotification;

public class Main implements Runnable {

	/**
	 * A command terminating the application
	 */
	private static final String EXIT_STRING = "quit";

	/**
	 * The payments to be printed
	 */
	private final Map<Currency, Money> payments;
	private final Scanner scanner;
	private final Runnable notifications;

	public static void main(String[] args) {
		final long period;
		if (args.length > 0 ) {
			period = Math.max(10L, Long.parseLong(args[0]));
		} else {
			period = 60;
		}
		new Main(period).run();
	}

	private Main(final long period) {
		this.scanner = new Scanner(System.in);
		this.payments = new EnumMap<>(Currency.class);
		this.notifications = new ConsoleNotification(period, () -> {
			System.out.println("========= TRACKED PAYMENTS =========");
			if (this.payments.isEmpty()) {
				System.out.println("There are no payments yet");
			} else {
				this.payments.forEach(((currency, money) -> money.print(Currency.USD)));
			}
			System.out.println("====================================");
		});
	}

	@Override
	public final void run() {
		final String currencies = Arrays.stream(Currency.values()).map(Currency::name).collect(Collectors.joining(", "));
		Arrays.asList(
			"Hi, welcome to the payment tracker ^___^",
			"Load a batch of transactions both from files and type them manually.",
			"If you want to load a file, type '>' and type a file name relative from here.",
			"If you want to ype a transaction manually, do it down to the console.",
			"In both cases, the format should follow the example 'USD 123'.",
			" Up to one transaction per line and decimal numbers are allowed.",
			String.format("Available currencies: %s", currencies),
			"Quit the application by typing 'quit', or kill the terminal...",
			""
		).forEach(System.out::println);

		this.notifications.run();

		String line;
		do {
			line = scanner.nextLine();
			if (line.startsWith(">")) {
				this.mergePayments(new FilePayments(line));
			} else if (!EXIT_STRING.equalsIgnoreCase(line)) {
				this.mergePayments(new StringPayments(line));
			}
		} while (!EXIT_STRING.equalsIgnoreCase(line));

		System.out.println("You have ordered to quit, see ya later ;)");
		System.exit(0);
	}

	/**
	 * A method merging the payments into the Map of all payments.{@link #mergePayments(List)}
	 * @param payments The payments to be processed
	 */
	private void mergePayments(final Payments... payments) {
		for (Payments p: payments) {
			this.mergePayments(p.payments());
		}
	}

	/**
	 * A method merging the payments into the Map of all payments.
	 * If the Currency exists among the keys, the payment is added up to the existing one.
	 * Otherwise a new entry is created. The currencies with 0 amount are removed.
	 * @param payments The payments to be processed
	 */
	private void mergePayments(final List<Money> payments) {
		for (final Money m: payments) {
			final BigDecimal amount = m.amount();
			final Currency currency = m.currency();
			this.payments.computeIfPresent(currency, (c, ma) -> ma.with(amount));
			this.payments.putIfAbsent(currency, m);
		}
		this.payments.entrySet().removeIf(entry -> BigDecimal.ZERO.compareTo(entry.getValue().amount()) == 0);
	}
}
