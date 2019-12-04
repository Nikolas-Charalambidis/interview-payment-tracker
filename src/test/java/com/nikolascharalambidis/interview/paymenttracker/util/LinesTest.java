package com.nikolascharalambidis.interview.paymenttracker.util;

import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LinesTest {

	private static final String EXISTING_FILE_NAME = "input.txt";
	private static final String WRONG_FILE_NAME = "none.txt";

	@Test
	void testValidFile() throws IOException {
		Assertions.assertEquals(
			Arrays.asList("usd 100", "", "gbp 100", "gbp 100", "malformed", "usd 100", "usd 100"),
			new Lines(Lines.class.getClassLoader().getResourceAsStream(EXISTING_FILE_NAME), EXISTING_FILE_NAME).get());
	}

	@Test
	void testInvalidFile() {
		Assertions.assertThrows(IOException.class, () ->
			new Lines(Lines.class.getClassLoader().getResourceAsStream(WRONG_FILE_NAME), WRONG_FILE_NAME));
	}
}
