package com.nikolascharalambidis.interview.paymenttracker.payments;

import java.util.List;

import com.nikolascharalambidis.interview.paymenttracker.money.Money;

/**
 * An interface returning the payments in form of money
 */
public interface Payments {

	List<Money> payments();
}
