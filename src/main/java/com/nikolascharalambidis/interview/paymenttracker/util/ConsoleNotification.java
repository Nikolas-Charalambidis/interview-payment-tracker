package com.nikolascharalambidis.interview.paymenttracker.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConsoleNotification implements Runnable {

	private final Runnable notification;
	private final ScheduledExecutorService notificationService;
	private final long period;

	public ConsoleNotification(final long period, final Runnable notification) {
		this.notification = notification;
		this.notificationService = Executors.newScheduledThreadPool(1);
		this.period = period;
	}

	@Override
	public void run() {
		this.notificationService.scheduleAtFixedRate(this.notification, 0, this.period, TimeUnit.SECONDS);
	}
}
