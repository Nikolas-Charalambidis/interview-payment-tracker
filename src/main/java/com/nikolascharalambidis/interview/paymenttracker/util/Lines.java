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

public class Lines implements Supplier<List<String>> {

	private final List<String> lines;

	public Lines(String fileName) throws IOException {
		this(new FileInputStream(fileName), fileName);
	}

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
	public List<String> get() {
		return lines;
	}
}
