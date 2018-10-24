package com.am.innovations.hibernate.benchmarks;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)

public class RESTBenchmark {

	private static final String DEPOSIT_URL = "http://localhost:8081/V_1.0/deposit?amount=1000&currency=USD&userID=1";
	private static final String WITHDRAW_URL = "http://localhost:8081/V_1.0/withdraw?amount=10&currency=USD&userID=1";
	private static final String BALANCE_URL = "http://localhost:8081/V_1.0/balance?userID=1";

	@Autowired
	private RestTemplate template;

	public RESTBenchmark() {
		template = new RestTemplate();
	}

	@Setup
	public void setup() {
	}

	@Benchmark
	public void testDeposit() throws URISyntaxException {
		RequestEntity<?> request1 = RequestEntity.post(new URI(DEPOSIT_URL)).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<String> response = template.exchange(request1, String.class);
	}

	@Benchmark
	public void testWithdraw() throws URISyntaxException {
		RequestEntity<?> request1 = RequestEntity.post(new URI(WITHDRAW_URL)).accept(MediaType.APPLICATION_JSON)
				.build();
		ResponseEntity<String> response = template.exchange(request1, String.class);
	}

	@Benchmark
	public void testGetBalance() throws URISyntaxException {
		RequestEntity<?> request1 = RequestEntity.get(new URI(BALANCE_URL)).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<String> response = template.exchange(request1, String.class);
	}

}
