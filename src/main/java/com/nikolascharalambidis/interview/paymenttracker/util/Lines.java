package com.nikolascharalambidis.interview.paymenttracker.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * A class loading all text lines from a text file returning them as a collection of lines
 */
public class Lines implements Supplier<List<String>> {

	/**
	 * The output lines
	 */
	private final List<String> lines;

	/**
	 * A secondary constructor
	 * @param fileName A file name
	 * @throws IOException An exception thrown if a resource cannot be loaded
	 */
	public Lines(String fileName) throws IOException {
		this(new FileInputStream(fileName), fileName);
	}

	/**
	 * A primary constructor
	 * @param inputStream InputStream as a file source
	 * @param fileName File name used just for the exception message
	 * @throws IOException An exception thrown if a resource cannot be loaded
	 */
	public Lines(InputStream inputStream, String fileName) throws IOException {
		final InputStreamReader inputStreamReader = Optional.ofNullable(inputStream)
			.map(InputStreamReader::new).orElseThrow(IOException::new);
		try (BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
			this.lines = bufferedReader.lines().collect(Collectors.toList());
		} catch (IOException e) {
			throw new IOException(String.format("Cannot load resource %s", fileName));
		}
	}

	@Override
	public final List<String> get() {
		return lines;
	}
}
