package com.nikolascharalambidis.interview.paymenttracker.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A console recurrent notification service
 */
public class ConsoleNotification implements Runnable {

	/**
	 * A Runnable to be notified with each period passed
	 */
	private final Runnable notification;

	/**
	 * A scheduler
	 */
	private final ScheduledExecutorService notificationService;

	/**
	 * A recurring period
	 */
	private final long period;

	public ConsoleNotification(final long period, final Runnable notification) {
		this.notification = notification;
		this.notificationService = Executors.newScheduledThreadPool(1);
		this.period = period;
	}

	@Override
	public final void run() {
		this.notificationService.scheduleAtFixedRate(this.notification, 0, this.period, TimeUnit.SECONDS);
	}
}
